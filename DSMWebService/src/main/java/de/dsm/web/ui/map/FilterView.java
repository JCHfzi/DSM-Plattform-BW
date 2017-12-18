package de.dsm.web.ui.map;



import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.gmap.GMap;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import de.dsm.data.dto.FlexibilityImportObject;
import de.dsm.data.dto.FlexibleCompanyAnfrageDTO;
import de.dsm.data.entity.Company;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;
import de.dsm.data.service.FlexibilityService;
import de.dsm.services.flex.DummyFlexibilityService;
import de.dsm.data.dto.FahrPlanAnfrageDTO;
import de.dsm.data.dto.FlexibilityDTO;
import de.dsm.data.service.FlexibilityService;
import de.dsm.data.dao.FlexibilityDAO;

import de.dsm.data.dto.FlexibilityImportObject;
import de.dsm.data.service.FlexibilityService;
import de.dsm.client.service.FahrplanService;
import de.dsm.client.service.MapService;
import de.dsm.web.ui.map.InfoWindowView;

@Component
//@ManagedBean(name="filterView")
//@SessionScoped
@Scope("session")
public class FilterView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public FlexibleCompanyAnfrageDTO dto;
	
	

	
	
	//Meta
	public String compName; //
	public String compAdresse; //
	public String regelzone; //
	public String spannungsebene; //
	public String geoLong; // LONGITUDE
	public String geoLat; // LATITUDE
	public String ne; // LONGITUDE
	public String sw; // LATITUDE
	
	
	/*
	var bounds = map.getBounds();
	var ne = bounds.getNorthEast(); // LatLng of the north-east corner
	var sw = bounds.getSouthWest(); // LatLng of the south-west corder */

	
	//Leistung
	public Integer posMinLeistung; //neg flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015P
	public Integer posMaxLeistung; //positive flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015P
	public Integer negMinLeistung; //neg flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015N
	public Integer negMaxLeistung; //positive flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015N
	
	//Stufe 1b
	public Integer minRampe; // maximale Änderungsrate der Bezugsleistung - DELTARAMP
	public String teilbarkeit; // Teilabruf von flexiblen Leistungen möglich -PDIVISIBILITY
	public Integer maxAktivierungsdauer; // Aktivierungsdauer; zeitlicher Vorlauf, der bis zur ersten geplanten Bezugsleistung notwendig ist - DACTIVATION

	
		public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompAdresse() {
		return compAdresse;
	}

	public void setCompAdresse(String compAdresse) {
		this.compAdresse = compAdresse;
	}

	public String getRegelzone() {
		return regelzone;
	}

	public void setRegelzone(String regelzone) {
		this.regelzone = regelzone;
	}

	public String getSpannungsebene() {
		return spannungsebene;
	}

	public void setSpannungsebene(String spannungsebene) {
		this.spannungsebene = spannungsebene;
	}

	public String getGeoLong() {
		return geoLong;
	}

	public void setGeoLong(String geoLong) {
		this.geoLong = geoLong;
	}

	public String getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(String geoLat) {
		this.geoLat = geoLat;
	}

	public Integer getPosMinLeistung() {
		return posMinLeistung;
	}

	public void setPosMinLeistung(Integer posMinLeistung) {
		this.posMinLeistung = posMinLeistung;
	}

	public Integer getPosMaxLeistung() {
		return posMaxLeistung;
	}

	public void setPosMaxLeistung(Integer posMaxLeistung) {
		this.posMaxLeistung = posMaxLeistung;
	}

	public Integer getNegMinLeistung() {
		return negMinLeistung;
	}

	public void setNegMinLeistung(Integer negMinLeistung) {
		this.negMinLeistung = negMinLeistung;
	}

	public Integer getNegMaxLeistung() {
		return negMaxLeistung;
	}

	public void setNegMaxLeistung(Integer negMaxLeistung) {
		this.negMaxLeistung = negMaxLeistung;
	}

	public Integer getMinRampe() {
		return minRampe;
	}

	public void setMinRampe(Integer minRampe) {
		this.minRampe = minRampe;
	}

	public String getTeilbarkeit() {
		return teilbarkeit;
	}

	public void setTeilbarkeit(String teilbarkeit) {
		this.teilbarkeit = teilbarkeit;
	}

	public Integer getMaxAktivierungsdauer() {
		return maxAktivierungsdauer;
	}

	public void setMaxAktivierungsdauer(Integer maxAktivierungsdauer) {
		this.maxAktivierungsdauer = maxAktivierungsdauer;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


public void reset(){
		
		this.compName ="";
		this.compAdresse="";

		this.regelzone="";	
		this.geoLong="";
		this.geoLat="";
		
		this.posMaxLeistung=100000;
		this.posMinLeistung=0;		
		this.negMinLeistung=0;	
		this.negMaxLeistung=100000;	
		this.minRampe=0;	
		this.teilbarkeit="nein";	
		this.maxAktivierungsdauer=1000;	

	}



	// Action beim klick des Restbuttons
		public void buttonActionReset() {
			
			this.reset();
			
			try {
				this.buttonAction();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	    }
	
		
		  
		// Action beim klick des Suchbuttons
		// public void buttonAction(ActionEvent actionEvent) {
	    public void buttonAction() throws IOException {
	    	
	          advancedModel = new DefaultMapModel();
		     
		      
		      
		      advancedModel.addOverlay(marker);
		       
		      
		      
		        
	    	
	    	
	    	//Erstelle DTO für Anfrage
			dto= new FlexibleCompanyAnfrageDTO(compName, compAdresse, regelzone, geoLong, geoLat, posMinLeistung,  posMaxLeistung, negMinLeistung,
					 negMaxLeistung,  minRampe,  teilbarkeit,  maxAktivierungsdauer);
	        
			
	 
	        //Gib passende Treffer zurück
	        this.passendeFlexis=null;
	        this.passendeFlexis = getService2().getFlexis(dto);
	        
	        
	        
	        //
	        
	        
	        // Wechsele auf Tab Fahrplan
	        // Für Steuerung der Tabs
	    	

	    	// Passe MapModel mit Markern an
	    	//InfoWindowView markerAufKarte =new InfoWindowView();
	    	//markerAufKarte.setPassendeFlexis(passendeFlexis);
	    	//markerAufKarte.addMarker();
	    	
	
	        
	        
	        
	        
	        if (passendeFlexis != null) {
	        	 for (Flexibility element : passendeFlexis) {
	                 
	             	//Shared coordinates
	        		 Company comp = element.getCompany();
  
	        		//LatLng coord = new LatLng(48.677530, 9.177992);
	        		LatLng coord = new LatLng(comp.getLatitude(),comp.getLongitude());
	             	
	        		
	        		// Verschiedene Marker?
	        		
	                 //Icons and Data	              
	                 advancedModel.addOverlay(new Marker(coord, comp.getCompanyLabel(), element, "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));
	                
	        	 }
	        }
	    	
	     // Query

	        
	    }

	/**
	 * 
	 */
	// Liste mit gefundenen Flexibilitäten
	private List<Flexibility> passendeFlexis;
	
	public List<Flexibility> getPassendeFlexis() {
		return passendeFlexis;
	}

	public void setPassendeFlexis(List<Flexibility> passendeFlexis) {
		this.passendeFlexis = passendeFlexis;
	}

//	@ManagedProperty("#{mapService}")
	@Autowired
    private MapService service2;
	
	
    public MapService getService2() {
		return service2;
	}


	public void setService2(MapService service2) {
		this.service2 = service2;
	}
	
	
	

    
    // Zeige zuerst alle Flexibilitäten
	@PostConstruct
	public void init(){
    	

		
    	//advancedModel = new DefaultMapModel();
    	

    	//geoLong = "48.689520"; // LONGITUDE
    	//geoLat = "9.187981"; // LATITUDE
    	//center = geoLong +", " + geoLat;
    	
    	//this.reset();
        // Zeige zurerst alle Flexis
      //  this.passendeFlexis = getService2().getFlexis(dto);
    	
    }


     

	private MapModel advancedModel;
	private GMap googleMap;
	  
    private Marker marker;

    
    
    
 

	public MapModel getAdvancedModel() {
		return advancedModel;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public GMap getGoogleMap() {
		return googleMap;
	}

	public void setGoogleMap(GMap googleMap) {
		this.googleMap = googleMap;
	}



}
