// TEst später Löschen
package de.dsm.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.FlexibilityDAO;
import de.dsm.data.entity.Flexibility;

@Component
@Transactional
public class FlexibilityService {

	@Autowired
	private FlexibilityDAO flexibilityDao;

	public FlexibilityDAO getFlexibilityDao() {
		return flexibilityDao;
	}

	public void setFlexibilityDao(FlexibilityDAO flexibilityDao) {
		this.flexibilityDao = flexibilityDao;
	}
	
	public List<Flexibility> getFiltered(String compName, String city, String regelzone,
			float minPosLeistung, float maxPosLeistung, float minNegLeistung, float maxNegLeistung, 
			float minRampe, float maxAktivierungsDauer) {
		return getFlexibilityDao().getFiltered(compName, city, regelzone, minPosLeistung, maxPosLeistung, minNegLeistung, maxNegLeistung, minRampe, maxAktivierungsDauer);
	}
	
	public Flexibility getById(Long flexId) {
		return getFlexibilityDao().getForId(flexId);
	}
	
}
