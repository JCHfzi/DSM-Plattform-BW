// Alte Version -> Spring ORM
package de.dsm.data.dao.impl;


import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.FlexibilityDAO;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.FlexiblePower;

@Repository
@Transactional
public class FlexibilityDAOImpl implements FlexibilityDAO {

	private static Logger log = Logger.getLogger(FlexiblePowerDAOImpl.class);
	private SessionFactory sessionFactory;
	 
	public SessionFactory getSessionFactory() {
	return sessionFactory;
	}
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	}
	

	@SuppressWarnings("unchecked")
	public Flexibility getForId(Long id) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Flexibility.class).add( Restrictions .eq("id", id ));
		List<Flexibility> flexibilities = (List<Flexibility>) criteria.list();
		
		if(flexibilities != null && flexibilities.size() > 0)
			return flexibilities.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	public List<Flexibility> getFiltered(String compName, String city, String regelzone,
			Float minPosLeistung, Float maxPosLeistung, Float minNegLeistung, Float maxNegLeistung, 
			Float minRampe, Float maxAktivierungsDauer) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Flexibility.class, "flex")
				.createAlias("flex.company", "company").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		if(compName != null && compName.length() > 0)
			criteria.add( Restrictions.like("company.companyLabel", compName, MatchMode.ANYWHERE));
	
		
		if(city != null && city.length() > 0)
			criteria.add( Restrictions.like("company.city", city, MatchMode.ANYWHERE ));

		//Regelzone
		if(regelzone != null && regelzone.length() > 0)
			criteria.add( Restrictions.like("flex.controlArea", regelzone, MatchMode.ANYWHERE ));
		
		// flex.posLeistung ist ein optionaler Parameter (Ich glaube der sagt etwas über die eingespeiste Leistung aus, nciht über die Flexibilität
		// Stattdessen sollte auf p015n und p015p gefiltert werden
		if(minPosLeistung != null && maxPosLeistung != null) {
			
			if(minPosLeistung==0){
				log.info("flex.p015p lower than  " + maxPosLeistung);
				criteria.add( Restrictions.le("p015p", maxPosLeistung ));
			} else {
				
			
			log.info("flex.p015p between " + minPosLeistung + " and " + maxPosLeistung);
			criteria.add( Restrictions.between("p015p", minPosLeistung, maxPosLeistung ));
			}
		}
		
		if(minNegLeistung != null && maxNegLeistung != null) {
			
			if(minNegLeistung==0){
				log.info("flex.p015n lower than  " + maxNegLeistung);
				criteria.add( Restrictions.le("p015n", maxNegLeistung ));
			} else {
			
			log.info("flex.p015n between " + minNegLeistung + " and " + maxNegLeistung);
			criteria.add( Restrictions.between("p015n", minNegLeistung, maxNegLeistung ));
			}
		}
		
		if(minRampe != null) {
			log.info("flex.rampe greater or equal then " + minRampe);
			criteria.add( Restrictions.ge("rampe", minRampe));
		}
		
		
		if(maxAktivierungsDauer != null) {
			log.info("flex.aktivierungsDauer lower or equal then " + maxAktivierungsDauer);
			criteria.add( Restrictions.le("flex.aktivierungsDauer", maxAktivierungsDauer ));
		}
		List<Flexibility> ret = criteria.list();
		
		log.info("# of found flexis: " + ret.size());
		return ret;
	}
	
}
