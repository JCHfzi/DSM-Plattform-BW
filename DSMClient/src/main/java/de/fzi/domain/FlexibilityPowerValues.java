package de.fzi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

public class FlexibilityPowerValues {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pMinus_4;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pMinus_1;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pMinus_1_4;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pPlus_4;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pPlus_1;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double pPlus_1_4;
	@JsonInclude(JsonInclude.Include.NON_NULL)  
	private String TS1;
	@JsonInclude(JsonInclude.Include.NON_NULL)
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
	
	/**
	 * Added a toString Method for testing the Result.
	 */
	@Override
	public String toString(){
		String result = "pPlus_1_4:" + getpPlus_1_4() + " pPlus_1: " + getpPlus_1() + " pPlus4: " + getpPlus_4() +
				" pMinus_1: " + getpMinus_1() + " getpMinus_4: " + getpMinus_4() +	" getpMinus_1_4: " + getpMinus_1_4() +
				" getTS1: " + getTS1() + " getTS2: " +getTS2() ;
		return result;
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

}
