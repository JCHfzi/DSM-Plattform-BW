package de.fraunhofer.iao.domain;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Class contains flexibility parameters which change in time
 * @author dsm
 *
 */
public class IAOFlexibility {

	Instant timestamp;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * positive flexible Leistung, die über eine Stunde hinweg bereitgestellt werden kann
	 */
	private String pPlus1;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	//positive flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann
	private String pPlus1_4;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * positive flexible Leistung, die über vier Stunde hinweg bereitgestellt werden kann
	 */
	private String pPlus4;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * negative flexible Leistung, die über eine Stunde hinweg bereitgestellt werden kann
	 */
	private String pMinus1;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * negative flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann
	 */
	private String pMinus1_4;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * negative flexible Leistung, die über vier Stunde hinweg bereitgestellt werden kann
	 */
	private String pMinus4;
	
	
	public String getpPlus1() {
		return pPlus1;
	}

	public void setpPlus1(String pPlus1) {
		this.pPlus1 = pPlus1;
	}

	public String getpPlus1_4() {
		return pPlus1_4;
	}

	public void setpPlus1_4(String pPlus1_4) {
		this.pPlus1_4 = pPlus1_4;
	}

	public String getpPlus4() {
		return pPlus4;
	}

	public void setpPlus4(String pPlus4) {
		this.pPlus4 = pPlus4;
	}

	public String getpMinus1() {
		return pMinus1;
	}

	public void setpMinus1(String pMinus1) {
		this.pMinus1 = pMinus1;
	}

	public String getpMinus1_4() {
		return pMinus1_4;
	}

	public void setpMinus1_4(String pMinus1_4) {
		this.pMinus1_4 = pMinus1_4;
	}

	public String getpMinus4() {
		return pMinus4;
	}

	public void setpMinus4(String pMinus4) {
		this.pMinus4 = pMinus4;
	}
}
