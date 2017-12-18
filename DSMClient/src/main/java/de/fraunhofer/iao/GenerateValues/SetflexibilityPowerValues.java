package de.fraunhofer.iao.GenerateValues;
import java.time.Instant;
import java.util.Random;
import org.apache.log4j.Logger;
import de.fraunhofer.iao.domain.Company;
import de.fraunhofer.iao.domain.IAOGeneratorConfiguration;
import de.fraunhofer.iao.main.DSMClientMain;
import de.fzi.domain.FlexibilityImportObject;
import de.fzi.domain.FlexibilityPowerValues;
/**
 * * Class is used to set new realistic Values and add the new Values to a List
 * * * * @author Laustere *
 */
public class SetflexibilityPowerValues {
	private static Logger log = Logger.getLogger(DSMClientMain.class);
	Random rand = new Random();
	Company comp;
	double maxMinus1;
	double minMinus1;
	double maxMinus4;
	double minMinus4;
	double maxMinus1_4;
	double minMinus1_4;
	double maxPlus1;
	double minPlus1;
	double maxPlus1_4;
	double minPlus1_4;
	double maxPlus4;
	double minPlus4;
	/**
	 * * Generate new Values for a Company. * * @param config * is the actual
	 * Company Object which values are new generated. * CompanyUpdate keeps all
	 * the Values.
	 */
	public void generateValues(IAOGeneratorConfiguration config,
			FlexibilityImportObject companyUpdate) {
		getAndConvertvalues(config);
		log.info("Generate Random Values.");
		int millies = 900000;
		Instant ts1 = Instant.now();
		Instant ts2 = ts1.plusMillis(millies);
		RealisticValueGenerator realisticValues = new RealisticValueGenerator();
		FlexibilityPowerValues flexibilityPowerValues = new FlexibilityPowerValues();
		flexibilityPowerValues.setpPlus_1(
				realisticValues.generateValue(minPlus1, maxPlus1, ts1));
		flexibilityPowerValues.setpPlus_4(
				realisticValues.generateValue(minPlus4, maxPlus4, ts1));
		flexibilityPowerValues.setpPlus_1_4(
				realisticValues.generateValue(minPlus1_4, maxPlus1_4, ts1));
		flexibilityPowerValues.setpMinus_1(
				realisticValues.generateValue(minMinus1, maxMinus1, ts1));
		flexibilityPowerValues.setpMinus_4(
				realisticValues.generateValue(minMinus4, maxMinus4, ts1));
		flexibilityPowerValues.setpMinus_1_4(
				realisticValues.generateValue(minMinus1_4, maxMinus1_4, ts1));
		
		flexibilityPowerValues.setTS1(ts1.toString());
		flexibilityPowerValues.setTS2(ts2.toString());
		
		String tempTs = flexibilityPowerValues.getTS1();
		
		// Problem mit Datums-Strings ohne Dezimalstellen
		
		if(tempTs.substring(tempTs.length()-5,tempTs.length()-4).equals(".") == false) {
			tempTs=tempTs.substring(0,tempTs.length()-4);
			tempTs=tempTs.concat(".000Z");
			flexibilityPowerValues.setTS1(tempTs);
		}
		tempTs = flexibilityPowerValues.getTS2();
		if(tempTs.substring(tempTs.length()-5,tempTs.length()-4).equals(".") == false) {
			tempTs=tempTs.substring(0,tempTs.length()-4);
			tempTs=tempTs.concat(".000Z");
			flexibilityPowerValues.setTS2(tempTs);
		}
		
		log.info("FlexbilityPowerValues Object: "
				+ flexibilityPowerValues.toString());
		companyUpdate.addFlexibilityPowerValues(flexibilityPowerValues);
	}
	public void getAndConvertvalues(IAOGeneratorConfiguration config) {
		double maxMinus1 = Double.parseDouble(config.getMaxpMinus1());
		setMaxMinus1(maxMinus1);
		double minMinus1 = Double.parseDouble(config.getMinpMinus1());
		setMinMinus1(minMinus1);
		double maxMinus4 = Double.parseDouble(config.getMaxpMinus4());
		setMaxMinus4(maxMinus4);
		double minMinus4 = Double.parseDouble(config.getMinpMinus4());
		setMinMinus4(minMinus4);
		double maxMinus1_4 = Double.parseDouble(config.getMaxpMinus1_4());
		setMaxMinus1_4(maxMinus1_4);
		double minMinus1_4 = Double.parseDouble(config.getMinpMinus1_4());
		setMinMinus1_4(minMinus1_4);
		double maxPlus1 = Double.parseDouble(config.getMaxpPlus1());
		setMaxPlus1(maxPlus1);
		double minPlus1 = Double.parseDouble(config.getMinpPlus1());
		setMinPlus1(minPlus1);
		double maxPlus1_4 = Double.parseDouble(config.getMaxpPlus1_4());
		setMaxPlus1_4(maxPlus1_4);
		double minPlus1_4 = Double.parseDouble(config.getMinpPlus1_4());
		setMinPlus1_4(minPlus1_4);
		double maxPlus4 = Double.parseDouble(config.getMaxpPlus4());
		setMaxPlus4(maxPlus4);
		double minPlus4 = Double.parseDouble(config.getMinpPlus4());
		setMinPlus4(minPlus4);
	}
	public double getMaxMinus1() {
		return maxMinus1;
	}
	public void setMaxMinus1(double maxMinus1) {
		this.maxMinus1 = maxMinus1;
	}
	public double getMinMinus1() {
		return minMinus1;
	}
	public void setMinMinus1(double minMinus1) {
		this.minMinus1 = minMinus1;
	}
	public double getMaxMinus4() {
		return maxMinus4;
	}
	public void setMaxMinus4(double maxMinus4) {
		this.maxMinus4 = maxMinus4;
	}
	public double getMinMinus4() {
		return minMinus4;
	}
	public void setMinMinus4(double minMinus4) {
		this.minMinus4 = minMinus4;
	}
	public double getMaxMinus1_4() {
		return maxMinus1_4;
	}
	public void setMaxMinus1_4(double maxMinus1_4) {
		this.maxMinus1_4 = maxMinus1_4;
	}
	public double getMinMinus1_4() {
		return minMinus1_4;
	}
	public void setMinMinus1_4(double minMinus1_4) {
		this.minMinus1_4 = minMinus1_4;
	}
	public double getMaxPlus1() {
		return maxPlus1;
	}
	public void setMaxPlus1(double maxPlus1) {
		this.maxPlus1 = maxPlus1;
	}
	public double getMinPlus1() {
		return minPlus1;
	}
	public void setMinPlus1(double minPlus1) {
		this.minPlus1 = minPlus1;
	}
	public double getMaxPlus1_4() {
		return maxPlus1_4;
	}
	public void setMaxPlus1_4(double maxPlus1_4) {
		this.maxPlus1_4 = maxPlus1_4;
	}
	public double getMinPlus1_4() {
		return minPlus1_4;
	}
	public void setMinPlus1_4(double minPlus1_4) {
		this.minPlus1_4 = minPlus1_4;
	}
	public double getMaxPlus4() {
		return maxPlus4;
	}
	public void setMaxPlus4(double maxPlus4) {
		this.maxPlus4 = maxPlus4;
	}
	public double getMinPlus4() {
		return minPlus4;
	}
	public void setMinPlus4(double minPlus4) {
		this.minPlus4 = minPlus4;
	}
}