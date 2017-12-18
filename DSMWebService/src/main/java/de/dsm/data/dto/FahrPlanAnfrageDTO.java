package de.dsm.data.dto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;




@ManagedBean(name="fahrPlanAnfrageDTO")
@ViewScoped
public class FahrPlanAnfrageDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FahrPlanAnfrageDTO fahrPlanAnfrageDTO;
	
	public FahrPlanAnfrageDTO getFahrPlanAnfrageDTO() {
		return fahrPlanAnfrageDTO;
	}

	public void setFahrPlanAnfrageDTO(FahrPlanAnfrageDTO fahrPlanAnfrageDTO) {
		this.fahrPlanAnfrageDTO = fahrPlanAnfrageDTO;
	}

	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
	}

	public String startzeit;
	public String vermarktungsoption;
	// in kW
	public String gesamtleistung;
	// in min
	public String aktivierungsdauer;
	// in warm, kalt
	public String umgebungstemperatur;
		// in Sommer, Winter
	public String jahreszeit;

	// in sonnig, bewölkt
	public String witterung;
	
	public String getVermarktungsoption() {
		return vermarktungsoption;
	}

	public void setVermarktungsoption(String vermarktungsoption) {
		this.vermarktungsoption = vermarktungsoption;
	}

	public String getGesamtleistung() {
		return gesamtleistung;
	}

	public void setGesamtleistung(String gesamtleistung) {
		this.gesamtleistung = gesamtleistung;
	}

	public String getAktivierungsdauer() {
		return aktivierungsdauer;
	}

	public void setAktivierungsdauer(String aktivierungsdauer) {
		this.aktivierungsdauer = aktivierungsdauer;
	}

	public String getUmgebungstemperatur() {
		return umgebungstemperatur;
	}

	public void setUmgebungstemperatur(String umgebungstemperatur) {
		this.umgebungstemperatur = umgebungstemperatur;
	}

	public String getJahreszeit() {
		return jahreszeit;
	}

	public void setJahreszeit(String jahreszeit) {
		this.jahreszeit = jahreszeit;
	}

	public String getWitterung() {
		return witterung;
	}

	public void setWitterung(String witterung) {
		this.witterung = witterung;
	}

	public FahrPlanAnfrageDTO(String startzeit, String vermarktungsoption, String gesamtleistung, String aktivierungsdauer,
			String umgebungstemperatur, String jahreszeit, String witterung) {
		super();
		this.startzeit = startzeit;
		this.vermarktungsoption = vermarktungsoption;
		this.gesamtleistung = gesamtleistung;
		this.aktivierungsdauer = aktivierungsdauer;
		this.umgebungstemperatur = umgebungstemperatur;
		this.jahreszeit = jahreszeit;
		this.witterung = witterung;
	}
	
	public FahrPlanAnfrageDTO() {
		
	}
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!FahrPlanAnfrageDTO.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final FahrPlanAnfrageDTO other = (FahrPlanAnfrageDTO) obj;

	    if (this.startzeit != other.startzeit) {
	        return false;
	    }
	    if (this.vermarktungsoption.equalsIgnoreCase(other.vermarktungsoption) == false ) {
	        return false;
	    }
	    if (this.gesamtleistung.equalsIgnoreCase(other.gesamtleistung) == false ) {
	        return false;
	    }
	    if (this.aktivierungsdauer.equalsIgnoreCase(other.aktivierungsdauer) == false ) {
	        return false;
	    }
	    if (this.umgebungstemperatur.equalsIgnoreCase(other.umgebungstemperatur) == false) {
	        return false;
	    }
	    if (this.jahreszeit.equalsIgnoreCase(other.jahreszeit) == false ) {
	        return false;
	    }
	    if (this.witterung.equalsIgnoreCase(other.witterung) == false ) {
	        return false;
	    }
	    
	    return true;
	}

	
}
