package de.dsm.data.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DateType;
import org.hibernate.type.TimestampType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.TimeseriesDAO;
import de.dsm.data.entity.Company;
import de.dsm.data.entity.Flexibility;
import de.dsm.data.entity.Timeseries;



@Repository
@Transactional
public class TimeseriesDAOImpl implements TimeseriesDAO {
	
	private static Logger log = Logger.getLogger(TimeseriesDAOImpl.class);
	private SessionFactory sessionFactory = null;

	@SuppressWarnings("unchecked")
	@Override
	public Timeseries findById(Long id) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Timeseries.class).add( Restrictions .eq("id", id ));
		List<Timeseries> timeseries = (List<Timeseries>) criteria.list();
		
		if(timeseries != null && timeseries.size() > 0)
			return timeseries.get(0);
		else return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Timeseries> findByFlexPwrIdAndPeriod(Long fpId, Date start, Date end) {
		Session session = getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Timeseries.class, "ts")
				.createAlias("ts.flexiblePower", "fpwr").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)				
				.add( Restrictions .eq("fpwr.sid", fpId ));
		criteria.add(Restrictions.ge("ts.start", start)).add(Restrictions.le("ts.end", end));
		List<Timeseries> ret = criteria.list();

		log.info("findByIdAndPeriod: " + ret.size());
		
		return ret;
	}
	
	
	@Override
	public Timeseries saveOrUpdate(Timeseries entity) {
       try
        {
    	   Session session = getSessionFactory().getCurrentSession();
            session.saveOrUpdate(entity);
            return entity;
        }
        catch (Exception ex)
        {
            String errorMessage = "Failed to save the object.";
            log.error(errorMessage, ex);
            throw new RuntimeException(errorMessage, ex);
        }
    }

	@SuppressWarnings("unchecked")
	public Double getAverageValue(Long id, long intervalSize) {
		Session session = getSessionFactory().getCurrentSession();
		BigDecimal result = (BigDecimal) session.createSQLQuery("select avg(x.val) "
				+ "from (select ts.value as val "
					+ "from  timeseries ts " 
					+ "where ts.SID = '" + id +"' order by ts.ID desc) x "
				+ "where rownum < " + intervalSize).list().get(0);
		
		return result.doubleValue();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> findPeriodByFlexIdAndNoOfPoints(Long flexId, long noOfPointsToFetch) {
		Session session = getSessionFactory().getCurrentSession();
		SimpleDateFormat _sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		List<Object[]> result = session.createSQLQuery(
					"select min(TS1) as min_date, max(TS2) as max_date " +
							"from ( " +
							"select * " +
							"from  " +
							"(select * " +
							"from timeseries ts " +
							"join flexible_power fp on fp.SID = ts.SID " +
							"join flexibility flex on flex.flexid = fp.flexid " +
							"where flex.flexid = "+flexId+" " +
							"order by ts.TS1 desc) " +
							"where rownum <= "+ noOfPointsToFetch + ")")
				.addScalar("min_date", TimestampType.INSTANCE)
				.addScalar("max_date", TimestampType.INSTANCE).list();
		List<Date> ret = new ArrayList<Date>();
		if(result != null && result.size() > 0 && result.get(0)[0] != null && result.get(0)[1] != null) {
			try {
				ret.add(0, _sdf.parse(result.get(0)[0].toString()));
				ret.add(1, _sdf.parse(result.get(0)[1].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}
}
