package de.dsm.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.FlexiblePowerDAO;
import de.dsm.data.entity.FlexiblePower;

@Service
@Transactional
public class FlexiblePowerService {

	@Autowired
	private FlexiblePowerDAO flexiblePowerDao;

	public FlexiblePower getFlexiblePower(long flexPwrId) {
		return getFlexiblePowerDAO().getForId(flexPwrId);
	}

	public FlexiblePowerDAO getFlexiblePowerDAO() {
		return flexiblePowerDao;
	}

	public void setFlexiblePowerDAO(FlexiblePowerDAO flexiblePowerDao) {
		this.flexiblePowerDao = flexiblePowerDao;
	}
}
