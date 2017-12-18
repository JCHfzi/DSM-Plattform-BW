package de.dsm.data.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.CompanyDAO;
import de.dsm.data.entity.Company;



@Repository
@Transactional
public class CompanyDAOImpl implements CompanyDAO {
	
	private SessionFactory sessionFactory = null;


	@SuppressWarnings("unchecked")
	public List<Company> getAll() {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class);
		return (List<Company>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public Company getForId(Long id) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class).add( Restrictions .eq("id", id ));
		List<Company> companies = (List<Company>) criteria.list();
		
		if(companies != null && companies.size() > 0)
			return companies.get(0);
		else return null;
	}

	public void listCompanies() {
		
		List<Company> compList = getAll();
	     
	     for(Company comp : compList) {
	    	 System.out.println("==================Company Details======================");
	    	 System.out.println("SID: " + comp.getCompId());
	    	 System.out.println("NAME : " + comp.getCompanyLabel());
	     }
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
