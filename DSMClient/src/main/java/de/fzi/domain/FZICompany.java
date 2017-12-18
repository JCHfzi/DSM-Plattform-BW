package de.fzi.domain;

import java.util.List;

//import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;


//TODO hinzugefügt vom IAO
/**
 * Simulates different Companys from Stage One A/B or Stage Two The Different
 * Values are crucial for the choice of the Constructor. Created by Lausterer on
 * 03.04.2017.
 */
public class FZICompany {

	//private static Logger log = Logger.getLogger(FZICompany.class);

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String pPlus1, pPlus1_4, pPlus4, pMinus1, pMinus1_4, pMinus4,
			tShiftMax, eShift, dS, dV, deltaRampe, dA, pTeilbar, verfügbarkeit,
			maxAktivierungen;

	@JsonIgnore
	private String uId, fId, maxpPlus1, minpPlus1, maxpMinus1, minpMinus1,
			maxpPlus1_4, minpPlus1_4, maxpMinus1_4, minpMinus1_4, maxpPlus4,
			minpPlus4, maxpMinus4, minpMinus4, timeInPast, ifFileExists;

	List<FlexAnlage> Anlagen;

	/**
	 * Constructor for Stage Two with the Values min and max inbetween the new
	 * Values are generated. OHNE TIMER.
	 * 
	 * @param generateFutureValuesLength
	 * @param generateFutureValues
	 */
	public FZICompany(String uId, String fId, String maxpPlus1,
			String minpPlus1, String maxpMinus1, String minpMinus1,
			String maxpPlus1_4, String minpPlus1_4, String maxpMinus1_4,
			String minpMinus1_4, String maxpPlus4, String minpPlus4,
			String maxpMinus4, String minpMinus4, List<FlexAnlage> Anlagen) {

		this.uId = uId;
		this.fId = fId;
		this.maxpPlus1 = maxpPlus1;
		this.minpPlus1 = minpPlus1;
		this.maxpMinus1 = maxpMinus1;
		this.minpMinus1 = minpMinus1;
		this.maxpPlus1_4 = maxpPlus1_4;
		this.minpPlus1_4 = minpPlus1_4;
		this.maxpMinus1_4 = maxpMinus1_4;
		this.minpMinus1_4 = minpMinus1_4;
		this.maxpPlus4 = maxpPlus4;
		this.minpPlus4 = minpPlus4;
		this.maxpMinus4 = maxpMinus4;
		this.minpMinus4 = minpMinus4;
		this.setAnlagen(Anlagen);

	}

	/**
	 * Getters and Setters for all the Values.
	 * 
	 */
	public String gettShiftMax() {
		return tShiftMax;
	}

	public void settShiftMax(String tShiftMax) {
		this.tShiftMax = tShiftMax;
	}

	public String geteShift() {
		return eShift;
	}

	public void seteShift(String eShift) {
		this.eShift = eShift;
	}

	public String getdS() {
		return dS;
	}

	public void setdS(String dS) {
		this.dS = dS;
	}

	public String getdV() {
		return dV;
	}

	public void setdV(String dV) {
		this.dV = dV;
	}

	public String getDeltaRampe() {
		return deltaRampe;
	}

	public void setDeltaRampe(String deltaRampe) {
		this.deltaRampe = deltaRampe;
	}

	public String getdA() {
		return dA;
	}

	public void setdA(String dA) {
		this.dA = dA;
	}

	public String getpTeilbar() {
		return pTeilbar;
	}

	public void setpTeilbar(String pTeilbar) {
		this.pTeilbar = pTeilbar;
	}

	public String getVerfügbarkeit() {
		return verfügbarkeit;
	}

	public void setVerfügbarkeit(String verfügbarkeit) {
		this.verfügbarkeit = verfügbarkeit;
	}

	public String getMaxAktivierungen() {
		return maxAktivierungen;
	}

	public void setMaxAktivierungen(String maxAktivierungen) {
		this.maxAktivierungen = maxAktivierungen;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

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

	public String getMaxpPlus1() {
		return maxpPlus1;
	}

	public void setMaxpPlus1(String maxpPlus1) {
		this.maxpPlus1 = maxpPlus1;
	}

	public String getMinpPlus1() {
		return minpPlus1;
	}

	public void setMinpPlus1(String minpPlus1) {
		this.minpPlus1 = minpPlus1;
	}

	public String getMaxpMinus1() {
		return maxpMinus1;
	}

	public void setMaxpMinus1(String maxpMinus1) {
		this.maxpMinus1 = maxpMinus1;
	}

	public String getMinpMinus1() {
		return minpMinus1;
	}

	public void setMinpMinus1(String minpMinus1) {
		this.minpMinus1 = minpMinus1;
	}

	public String getMaxpPlus1_4() {
		return maxpPlus1_4;
	}

	public void setMaxpPlus1_4(String maxpPlus1_4) {
		this.maxpPlus1_4 = maxpPlus1_4;
	}

	public String getMinpPlus1_4() {
		return minpPlus1_4;
	}

	public void setMinpPlus1_4(String minpPlus1_4) {
		this.minpPlus1_4 = minpPlus1_4;
	}

	public String getMaxpMinus1_4() {
		return maxpMinus1_4;
	}

	public void setMaxpMinus1_4(String maxpMinus1_4) {
		this.maxpMinus1_4 = maxpMinus1_4;
	}

	public String getMinpMinus1_4() {
		return minpMinus1_4;
	}

	public void setMinpMinus1_4(String minpMinus1_4) {
		this.minpMinus1_4 = minpMinus1_4;
	}

	public String getMaxpPlus4() {
		return maxpPlus4;
	}

	public void setMaxpPlus4(String maxpPlus4) {
		this.maxpPlus4 = maxpPlus4;
	}

	public String getMinpPlus4() {
		return minpPlus4;
	}

	public void setMinpPlus4(String minpPlus4) {
		this.minpPlus4 = minpPlus4;
	}

	public String getMaxpMinus4() {
		return maxpMinus4;
	}

	public void setMaxpMinus4(String maxpMinus4) {
		this.maxpMinus4 = maxpMinus4;
	}

	public String getMinpMinus4() {
		return minpMinus4;
	}

	public void setMinpMinus4(String minpMinus4) {
		this.minpMinus4 = minpMinus4;
	}

	public String getIfFileExists() {
		return ifFileExists;
	}

	public void setIfText(String ifFileExists) {
		this.ifFileExists = ifFileExists;
	}

	public String getTimeInPast() {
		return timeInPast;
	}

	public void setTimeInPast(String timeInPast) {
		this.timeInPast = timeInPast;
	}

	public void setIfFileExists(String ifFileExists) {
		this.ifFileExists = ifFileExists;
	}
	public List<FlexAnlage> getAnlagen() {
		return Anlagen;
	}

	public void setAnlagen(List<FlexAnlage> anlagen) {
		Anlagen = anlagen;
	}

}
