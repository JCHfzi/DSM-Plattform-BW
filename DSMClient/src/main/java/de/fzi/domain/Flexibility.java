package de.fzi.domain;



import com.fasterxml.jackson.annotation.JsonInclude;

public class Flexibility {

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
    private String Uid;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String Fid;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String TS1;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    private String TS2;
    
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
