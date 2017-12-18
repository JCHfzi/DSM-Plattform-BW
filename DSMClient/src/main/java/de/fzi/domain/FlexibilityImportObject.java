package de.fzi.domain;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlexibilityImportObject {

	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String Uid;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String Fid;
	
	public FlexibilityImportObject(String uid, String fid,
			List<FlexibilityPowerValues> zeitreihe) {
		super();
		Uid = uid;
		Fid = fid;
		Zeitreihe = zeitreihe;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("zeitreihe")
	private List<FlexibilityPowerValues> Zeitreihe = new ArrayList<>();
	
	
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
	@JsonProperty("zeitreihe")
	public List<FlexibilityPowerValues> getZeitreihen() {
		return Zeitreihe;
	}
	@JsonProperty("zeitreihe")
	public void setZeitreihen(List<FlexibilityPowerValues> zeitreihe) {
		Zeitreihe = zeitreihe;
	}
	
	
	@JsonCreator
	public FlexibilityImportObject() {

	}
	
	public void addFlexibilityPowerValues(FlexibilityPowerValues powerValues) {
		Zeitreihe.add(powerValues);
		
	}
	
}
