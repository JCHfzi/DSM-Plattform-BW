package de.fzi.domain;

public class FlexAnlage {
    private String Fid;
    private Double pMin;
    private Double pMax;
    private String Typ;   
    private Double VerschiebeHorizont;
    private String Einheit; 
    
    public FlexAnlage(String Fid, Double pMin, Double pMax, String Typ, Double VerschiebeHorizont, String Einheit) {

		this.Fid = Fid;
		this.Einheit = Einheit;
		this.setTyp(Typ);
		this.setVerschiebeHorizont(VerschiebeHorizont);		

		
		if ("MWH".equalsIgnoreCase(Einheit)) {
			this.pMin = pMin*1000;
			this.pMax = pMax*1000;
		}else {
			this.pMin = pMin;
			this.pMax = pMax;
		}
		
		
    }
    
	public String getEinheit() {
		return Einheit;
	}

	public void setEinheit(String einheit) {
		Einheit = einheit;
	}

	public String getFid() {
		return Fid;
	}
	public void setFid(String fid) {
		Fid = fid;
	}
	public Double getpMin() {
		return pMin;
	}
	public void setpMin(Double pMin) {
		this.pMin = pMin;
	}
	public Double getpMax() {
		return pMax;
	}
	public void setpMax(Double pMax) {
		this.pMax = pMax;
	}

	public String getTyp() {
		return Typ;
	}

	public void setTyp(String typ) {
		Typ = typ;
	}

	public Double getVerschiebeHorizont() {
		return VerschiebeHorizont;
	}

	public void setVerschiebeHorizont(Double verschiebeHorizont) {
		VerschiebeHorizont = verschiebeHorizont;
	}
	
	
}
