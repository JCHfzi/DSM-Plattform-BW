// BEéschreibt das JSON, das über die REST -Schnittstelle kommt
package de.dsm.data.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlexibilityImportObject {

	private Long Uid;

	private Long Fid;

	public FlexibilityImportObject(Long uid, Long fid, List<FlexibilityPowerValues> zeitreihe) {
		super();
		Uid = uid;
		Fid = fid;
		Zeitreihe = zeitreihe;
	}

	@JsonProperty("zeitreihe")
	private List<FlexibilityPowerValues> Zeitreihe;

	public Long getUid() {
		return Uid;
	}

	public void setUid(Long uid) {
		Uid = uid;
	}

	public Long getFid() {
		return Fid;
	}

	public void setFid(Long fid) {
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
}
