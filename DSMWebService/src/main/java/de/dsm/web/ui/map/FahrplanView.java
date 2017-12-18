package de.dsm.web.ui.map;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;

import de.dsm.data.dto.FahrPlanAnfrageDTO;
import de.dsm.data.service.FlexibilityService;
import de.dsm.client.service.FahrplanService;


@ManagedBean(name="FahrplanView")
@ViewScoped
public class FahrplanView implements Serializable {
	
	   
	private static final long serialVersionUID = 1L;
	
	// Tab Index um zu Tab Fahrpläne zu wechseln

	
	public String fahrplanBildPfad;
	public FahrPlanAnfrageDTO dto;
	
	
	@ManagedProperty("#{fahrplanService}")
    private FahrplanService service;
	
	
    public FahrplanService getService() {
		return service;
	}


	public void setService(FahrplanService service) {
		this.service = service;
	}


	@PostConstruct
    public void init() {

		this.fahrplanBildPfad = "/resources/images/fahrplaene/KeinFahrplan.png";
		this.vermarktungsoption="Select One";
		this.gesamtleistung="Select One";
		this.umgebungstemperatur="Select One";
		this.jahreszeit="Select One";
		this.witterung="Select One";
		this.aktivierungsdauer="Select One";
	}
    
    
    /**
	 * 
	 */
	   public FahrplanView() {
		    //default contructor
		    }


	// Mögliche Eingabe/Suchoptionen
	// Vermarktungsoption PRL, SRL, MR
	public String vermarktungsoption;
	
	// in kW
	public String gesamtleistung;
	
	// in min
	public String aktivierungsdauer;
	
	// in warm, kalt
	public String umgebungstemperatur;
	
	// in Sommer, Winter
	public String jahreszeit;
	
	// hh:mm
	public String startzeit;
	
	
	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
	}

	// in sonnig, bewölkt
	public String witterung;
	
	

	
	
	public String getFahrplanBildPfad() {
		return fahrplanBildPfad;
	}

	public void setFahrplanBildPfad(String fahrplanBildPfad) {
		this.fahrplanBildPfad = fahrplanBildPfad;
	}



	//Getter Setter
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
	


	// Action beim klick des Restbuttons
	public void buttonActionReset() {

        // Setze Suchmaske zurück
		this.vermarktungsoption="Select One";
		this.gesamtleistung="Select One";
		this.umgebungstemperatur="Select One";
		this.jahreszeit="Select One";
		this.witterung="Select One";
		this.aktivierungsdauer="Select One";
		
		System.out.println(this.vermarktungsoption);
		
		//Zeige keinen Fahrplan an
		this.fahrplanBildPfad = "/resources/images/fahrplaene/KeinFahrplan.png";
    }
	
	// Action beim klick des Suchbuttons
	// public void buttonAction(ActionEvent actionEvent) {
	 

	
	
	public void buttonAction() {
		
		this.setVermarktungsoption(vermarktungsoption);
		this.setStartzeit(startzeit);
		this.setGesamtleistung(gesamtleistung);
		this.setAktivierungsdauer(aktivierungsdauer);
		this.setUmgebungstemperatur(umgebungstemperatur);
		this.setJahreszeit(jahreszeit);
		this.setWitterung(witterung);
		
		
		
    	
        //Erstelle DTO
		
		dto= new FahrPlanAnfrageDTO(startzeit, vermarktungsoption, gesamtleistung, aktivierungsdauer, umgebungstemperatur,  jahreszeit,  witterung);
		
        // Finde passenden Fahrplan und gib den Pfad zurück
 
        this.fahrplanBildPfad = getService().getFahrplanPath(dto);
        
        // Wechsele auf Tab Fahrplan
        // Für Steuerung der Tabs


    }
	


	
}
