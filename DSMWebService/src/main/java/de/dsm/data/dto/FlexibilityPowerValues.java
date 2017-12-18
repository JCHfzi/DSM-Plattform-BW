// BEéschreibt das JSON, das über die REST -Schnittstelle kommt
package de.dsm.data.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class FlexibilityPowerValues {

	private static Logger log = Logger.getLogger(FlexibilityPowerValues.class);

	private Double pMinus_4;

	private Double pMinus_1;

	private Double pMinus_1_4;

	private Double pPlus_4;

	private Double pPlus_1;

	private Double pPlus_1_4;
 
	private String TS1;

    private String TS2;
	
	public FlexibilityPowerValues() {
		
	}	
	
	public FlexibilityPowerValues(Double pMinus_4, Double pMinus_1,
			Double pMinus_1_4, Double pPlus_4, Double pPlus_1, Double pPlus_1_4,
			String tS1, String tS2) {
		super();
		this.pMinus_4 = pMinus_4;
		this.pMinus_1 = pMinus_1;
		this.pMinus_1_4 = pMinus_1_4;
		this.pPlus_4 = pPlus_4;
		this.pPlus_1 = pPlus_1;
		this.pPlus_1_4 = pPlus_1_4;
		TS1 = tS1;
		TS2 = tS2;
	}
	
	public Double getpMinus_4() {
		return pMinus_4;
	}
	public void setpMinus_4(Double pMinus_4) {
		this.pMinus_4 = pMinus_4;
	}
	public Double getpMinus_1() {
		return pMinus_1;
	}
	public void setpMinus_1(Double pMinus_1) {
		this.pMinus_1 = pMinus_1;
	}
	public Double getpMinus_1_4() {
		return pMinus_1_4;
	}
	public void setpMinus_1_4(Double pMinus_1_4) {
		this.pMinus_1_4 = pMinus_1_4;
	}
	public Double getpPlus_4() {
		return pPlus_4;
	}
	public void setpPlus_4(Double pPlus_4) {
		this.pPlus_4 = pPlus_4;
	}
	public Double getpPlus_1() {
		return pPlus_1;
	}
	public void setpPlus_1(Double pPlus_1) {
		this.pPlus_1 = pPlus_1;
	}
	public Double getpPlus_1_4() {
		return pPlus_1_4;
	}
	public void setpPlus_1_4(Double pPlus_1_4) {
		this.pPlus_1_4 = pPlus_1_4;
	}
	public String getTS1() {
		return TS1;
	}
	public void setTS1(String tS1) {
		TS1 = tS1;
	}
	public String getTS2() {
		return TS2;
	}
	public void setTS2(String tS2) {
		TS2 = tS2;
	}

	public Date getTS2asDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			return sdf.parse(TS2);
		} catch (Exception e) {
			log.error("TS2=" + TS2 + " does not seem to be a valid date string", e);
			return null;
		}
	}
	
	public Date getTS1asDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			return sdf.parse(TS1);
		} catch (Exception e) {
			log.error("TS1=" + TS1 + " does not seem to be a valid date string", e);
			return null;
		}
	}
	
	public String printString() {
		return "{pMinus_4 = " + pMinus_4 + "," +
				"pMinus_1 = " + pMinus_1 + "," +
				"pMinus_1_4 = " + pMinus_1_4 + "," +
				"pPlus_4 = " + pPlus_4 + "," +
				"pPlus_1 = " + pPlus_1 + "," +
				"pPlus_1_4 = " + pPlus_1_4 + "," +
				"TS1 = " + TS1 + "," +
				"TS2 = " + TS2 + "}";
	}
}
