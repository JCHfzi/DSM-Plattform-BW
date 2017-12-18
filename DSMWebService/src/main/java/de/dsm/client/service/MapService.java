// Mapt Filteranfrage auf Datenbankabfrage und gibt Liste mit FlexibleCompanyEntities aus

package de.dsm.client.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.dsm.data.dao.FlexiblePowerDAO;
import de.dsm.data.dto.FlexibleCompanyAnfrageDTO;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.service.FlexibilityService;



@Component
@Scope("session")
public class MapService {
	
	@Autowired
	private FlexibilityService flexibilityService;

	@Autowired
	private FlexiblePowerDAO flexiblePowerDao;

		
	//Methode die Anfrage erhält und Pfad zurückgibt
	public List<Flexibility> getFlexis(FlexibleCompanyAnfrageDTO anfrage){
		List<Flexibility> results = new ArrayList<Flexibility>();
		results.clear();
	
	
		float minPosLeistung = (anfrage.getPosMinLeistung() != null?anfrage.getPosMinLeistung().floatValue():0.0f);
		float maxPosLeistung = (anfrage.getPosMaxLeistung() != null?anfrage.getPosMaxLeistung().floatValue():100000.0f);
		float minNegLeistung = (anfrage.getNegMinLeistung() != null?anfrage.getNegMinLeistung().floatValue():0.0f); // 
		float maxNegLeistung = (anfrage.getNegMaxLeistung() != null?anfrage.getNegMaxLeistung().floatValue():100000.0f);
		float minRampe = (anfrage.getMinRampe() != null?anfrage.getMinRampe().floatValue():0.0f); // Default Werte richtig?
		float maxAktivierungsdauer = (anfrage.getMaxAktivierungsdauer() != null?anfrage.getMaxAktivierungsdauer().floatValue():1000.0f);

		
		
		return flexibilityService.getFiltered(anfrage.getCompName(), anfrage.getCompAdresse(), anfrage.getRegelzone(), 
				minPosLeistung, maxPosLeistung, 
				   minNegLeistung, maxNegLeistung,
				minRampe, 
				maxAktivierungsdauer);
	}


	public FlexibilityService getFlexiblePowerService() {
		return flexibilityService;
	}


	public void setFlexibilityService(FlexibilityService flexibilityService) {
		this.flexibilityService = flexibilityService;
	}


	public FlexiblePowerDAO getFlexiblePowerDao() {
		return flexiblePowerDao;
	}


	public void setFlexiblePowerDao(FlexiblePowerDAO flexiblePowerDao) {
		this.flexiblePowerDao = flexiblePowerDao;
	}
	
}
