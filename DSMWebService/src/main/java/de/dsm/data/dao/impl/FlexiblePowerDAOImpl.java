package de.dsm.data.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.FlexiblePowerDAO;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;



@Repository
@Transactional
public class FlexiblePowerDAOImpl implements FlexiblePowerDAO {
	
	private SessionFactory sessionFactory;
	private static Logger log = Logger.getLogger(FlexiblePowerDAOImpl.class);
	 
	 

	@SuppressWarnings("unchecked")
	public List<FlexiblePower> getForFlexibility(Flexibility flex) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(FlexiblePower.class).add( Restrictions .eq("flexibility", flex ));
		return (List<FlexiblePower>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public FlexiblePower getForId(Long id) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(FlexiblePower.class).add( Restrictions .eq("id", id ));
		List<FlexiblePower> flexPowers = (List<FlexiblePower>) criteria.list();
		
		if(flexPowers != null && flexPowers.size() > 0)
			return flexPowers.get(0);
		else return null;
	}

	public void releaseResources() {
		sessionFactory.close();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
