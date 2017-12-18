package de.fraunhofer.iao.GenerateValues;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.fzi.domain.FlexibilityPowerValues;

/**
 * Object for updating the values for the different Flexibilityfacilities.
 * 
 * @author dsm
 *
 */
public class CompanyUpdateDTO {

	private String Uid;
	private String Fid;
	private List<FlexibilityPowerValues> flexibilityPowerValuesList = new ArrayList<>();

	public void addFlexibilityPowerValues(
			FlexibilityPowerValues flexibilityPowerValuesIAO) {
		flexibilityPowerValuesList.add(flexibilityPowerValuesIAO);
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}

	public String getFid() {
		return Fid;
	}

	public void setFid(String fid) {
		Fid = fid;
	}

	@JsonProperty("zeitreihen")
	public List<FlexibilityPowerValues> getFlexibilityPowerValuesList() {
		return flexibilityPowerValuesList;
	}

	public void setFlexibilityPowerValuesList(
			List<FlexibilityPowerValues> flexibilityPowerValuesList) {
		this.flexibilityPowerValuesList = flexibilityPowerValuesList;
	}

}
