package de.dsm.web.ui.map;



import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.dsm.data.dto.FlexibleCompanyAnfrageDTO;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;
import de.dsm.data.entity.Timeseries;
import de.dsm.data.service.TimeseriesService;

@Component
@Scope("session")
public class TimeseriesView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(TimeseriesView.class);
	
	public FlexibleCompanyAnfrageDTO dto;
 
    private LineChartModel lineModel1 = null;
    
    private Flexibility flexibility; 
    private Date start;
    private Date end;

    private double min = 99999999;
    private double max = -99999999;
    private int no = 1;
    private Date xMin = null;
    private Date xMax = null;

    
	@Autowired
    private TimeseriesService tsService;
	
	
    public LineChartModel getLineModel() {
    	if(lineModel1 == null)
    		reload();
        return lineModel1;
    }
 

    public void reload() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        min = 99999999;
        max = -99999999;
        no = 1;
		long medianTS = getEnd().getTime() - (getEnd().getTime() - getStart().getTime())/2;
        xMin = new Date(medianTS);
        xMax = new Date(medianTS);
        log.info("resetting view with period <" + sdf.format(start) + "," + sdf.format(end) + "> " + sdf.format(xMin) + "," + sdf.format(xMax));
    	lineModel1 = initLinearModel(start, end);
    }
    
    private LineChartModel initLinearModel(Date start, Date end) {

        LineChartModel model = new LineChartModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        model.setTitle("Zeitreihen für " + flexibility.getLabel() + " - " + flexibility.getCompany().getCompanyLabel());
        model.setLegendPosition("e");
        model.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
        model.setResetAxesOnResize(true);

        if(flexibility != null) {
        	for(FlexiblePower flexPwr: flexibility.getFlexiblePowers()) {
                addSeriesToChart(flexPwr, start, end, model);
        	}

	        Axis yAxis = model.getAxis(AxisType.Y);
	        yAxis.setMin(min);
	        yAxis.setMax(max);
	
	        Axis xAxis = new DateAxis("Datum");
	        xAxis.setTickFormat("%Y.%m.%d %H:%#M:%S");
	        xAxis.setTickAngle(-70);
	        if(xMin != null && xMax != null) {
		        xAxis.setMin(sdf.format(xMin));
		        xAxis.setMax(sdf.format(xMax));
		        log.info("xmin=" + sdf.format(xMin) + " xMax=" + sdf.format(xMax));
	        }
	        model.getAxes().put(AxisType.X, xAxis);
	        log.info("no of entries: " + no + " min=" + min + " max=" + max);
        }
        return model;
    }

	private void addSeriesToChart(FlexiblePower flexPwr, Date start, Date end, LineChartModel model) {
		List<Timeseries> tsList = tsService.getTimeseriesEntriesForPeriod(flexPwr.getSid(), start, end);
        LineChartSeries series1 = new LineChartSeries();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        series1.setLabel("FlexPwr: " + flexPwr.getCategory());
        
        
        
        for(Timeseries ts: tsList) {
        	double val = ts.getSignedValue().doubleValue();
            series1.set(sdf.format(ts.getMedianTS()), val);
            if(min > val) min = val;
            if(max < val) max = val;
            if(xMin == null || xMin.after(ts.getMedianTS())) xMin = ts.getMedianTS(); 
            if(xMax == null || xMax.before(ts.getMedianTS())) xMax = ts.getMedianTS(); 
            no++;
        }
 
        model.addSeries(series1);
	}

	public Flexibility getFlexibility() {
		return flexibility;
	}

	public void setFlexibility(Flexibility flexibility) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		this.flexibility = flexibility;

    	lineModel1 = null;
    	
    	List<Date> period = tsService.getPeriodForFlexibilityAndNoOfRecordsPeriod(flexibility.getId(), 100l * 6);

    	if(period != null && period.size() == 2) {
    		log.info("results fetched:" + period.size() + " - " + sdf.format(period.get(0)) + " - " + sdf.format(period.get(1)));
    		start = period.get(0);
    		end = period.get(1);
    	} else {
    		log.warn("unable to determine optimal period. using default period!");
    		end = new Date();
	    	try {
	    		start = sdf.parse("2017-08-20 00:00:00.0");
			} catch (ParseException e) {
				e.printStackTrace();
			}
    	}
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
}
