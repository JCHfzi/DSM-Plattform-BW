package de.fraunhofer.iao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used for the Configuration of the Data Generator.
 * 
 * @author Nico Lausterer
 *
 */
public class IAOGeneratorConfiguration {

	@JsonIgnore
	private String maxpPlus1, minpPlus1, maxpMinus1, minpMinus1, maxpPlus1_4,
			minpPlus1_4, maxpMinus1_4, minpMinus1_4, maxpPlus4, minpPlus4,
			maxpMinus4, minpMinus4;

	/**
	 * timeInPast stays for the Time we generate in the Past. ifFileExists
	 * decide if we have to generate Data or if we can read Data out of a file.
	 */
	@JsonIgnore
	private String timeInPast, ifFileExists;

	/**
	 * The following min and max Values decide the Rang inbetween the Values are
	 * Generated.
	 */
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
}
