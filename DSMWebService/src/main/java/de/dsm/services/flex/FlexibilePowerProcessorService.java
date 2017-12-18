package de.dsm.services.flex;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.dsm.data.dto.FlexibilityPowerValues;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;
import de.dsm.data.entity.Timeseries;
import de.dsm.data.service.TimeseriesService;
import de.dsm.services.ProcessorResultCodes;

@Component
public class FlexibilePowerProcessorService {
	private static Logger log = Logger.getLogger(FlexibilePowerProcessorService.class);
	
	// min and max values for the flex powers. For now these are global constants but should be definable per flexibility
	private static final double _MIN_FLEX_PWR_VALUE = 0.0;
	private static final double _MAX_FLEX_PWR_VALUE = 100000.0;
	
	
	@Autowired
	private TimeseriesService timeseriesService;
	
    public ProcessorResultCodes processNewValues(Flexibility flex, List<FlexibilityPowerValues> flexiblePowerValues) {
    	ProcessorResultCodes resCode = ProcessorResultCodes.OK;
		// determine the corresponding flexible powers
		FlexiblePower fpP_015_p = flex.getFlexiblePowerByCategoryCode("P_015_p"); 
		FlexiblePower fpP_015_n = flex.getFlexiblePowerByCategoryCode("P_015_n"); 
		FlexiblePower fpP_060_p = flex.getFlexiblePowerByCategoryCode("P_060_p"); 
		FlexiblePower fpP_060_n = flex.getFlexiblePowerByCategoryCode("P_060_n"); 
		FlexiblePower fpP_240_p = flex.getFlexiblePowerByCategoryCode("P_240_p"); 
		FlexiblePower fpP_240_n = flex.getFlexiblePowerByCategoryCode("P_240_n");

		if(fpP_015_p != null && fpP_015_n != null &&
				fpP_060_p != null && fpP_060_n != null &&
				fpP_240_p != null && fpP_240_n != null) {
			
			// for now errors are just detected and the corresponding fpvs are not processed/stored. Fpvs without errors are stored.
			// for the future it must be decided, what should happen when a mixture of valid entries and errors occurs ....
			// - process valid  and report errors (partial processing)
			// - report errors and do not process any fpvs if errors occur (everything or nothing)
			for(FlexibilityPowerValues fpv: flexiblePowerValues) {
				Date start = fpv.getTS1asDate();
				Date end = fpv.getTS2asDate();
				if(start!= null && end != null) {
						
					if(this.checkThesholds(fpv, flex)) {
						this.storeTimeseriesRecord(fpP_015_p, fpv.getpPlus_1_4(), start, end);
						this.storeTimeseriesRecord(fpP_015_n, fpv.getpMinus_1_4(), start, end);
						this.storeTimeseriesRecord(fpP_060_p, fpv.getpPlus_1(), start, end);
						this.storeTimeseriesRecord(fpP_060_n, fpv.getpMinus_1(), start, end);
						this.storeTimeseriesRecord(fpP_240_p, fpv.getpPlus_4(), start, end);
						this.storeTimeseriesRecord(fpP_240_n, fpv.getpMinus_4(), start, end);
					} else {
						resCode = ProcessorResultCodes.ERRORS_OCCURRED;
						log.error("FlexiblePowerValue exceeded thresholds: " + fpv.printString());
					}
				} else {
					log.error("{\"state\":\"error\",\"description\":\"invalid date format\" }");
					return ProcessorResultCodes.INVALID_DATE_FORMAT;
				}
			}
		} else {
			log.error("{\"state\":\"error\",\"description\":\"flexible power not found\" }");
			return ProcessorResultCodes.UNKNOWN_FLEX_PWR;
		}
		
		return resCode;
    }
 
    private boolean checkOutlier(FlexiblePower fp, Double value) {
		double avg = timeseriesService.getAverageValue(fp.getSid(), 10);
		double deviation = Math.abs(avg-value);
		double dev_percentage = deviation/avg*100;
		log.info("Value deviation of FlexPwr " + fp.getSid() + ":" + avg + "-" + value + "=" + deviation + " --> %" + dev_percentage);
		
		return dev_percentage < 20.0; 
    	
    }
    
    private boolean checkThesholds(FlexibilityPowerValues fpv, Flexibility flex) {
    	return checkThresholds(fpv.getpMinus_1_4(), flex) &&
    			checkThresholds(fpv.getpPlus_1_4(), flex) &&
    			checkThresholds(fpv.getpMinus_1(), flex) &&
    			checkThresholds(fpv.getpPlus_1(), flex) &&
    			checkThresholds(fpv.getpMinus_4(), flex) &&
    			checkThresholds(fpv.getpPlus_4(), flex);
    }
    
    private boolean checkThresholds(double value, Flexibility flex) {
    	
    	return _MIN_FLEX_PWR_VALUE <= value && _MAX_FLEX_PWR_VALUE >= value;
    }
    
	private void storeTimeseriesRecord(FlexiblePower fp, Double value, Date start, Date end) {
		Timeseries ts = new Timeseries();
		ts.setFlexiblePower(fp);
		ts.setValue(BigDecimal.valueOf(value));
		ts.setStart(start);
		ts.setEnd(end);
		
		ts = timeseriesService.saveTimeseriesEntry(ts);
	}

}
