package de.dsm.data.dao;

import java.util.List;

import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;

public interface FlexiblePowerDAO {
	public List<FlexiblePower> getForFlexibility(Flexibility flex);
	public FlexiblePower getForId(Long id);
}
