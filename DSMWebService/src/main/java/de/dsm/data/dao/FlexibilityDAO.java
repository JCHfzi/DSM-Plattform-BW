// Alte Version -> Spring ORM
package de.dsm.data.dao;

import java.util.List;

import de.dsm.data.entity.Flexibility;

public interface FlexibilityDAO {

	public Flexibility getForId(Long id);

	public List<Flexibility> getFiltered(String compName, String city, String regelzone,
			Float minPosLeistung, Float maxPosLeistung, Float minNegLeistung, Float maxNegLeistung, 
			Float minRampe, Float maxAktivierungsDauer);	

}
