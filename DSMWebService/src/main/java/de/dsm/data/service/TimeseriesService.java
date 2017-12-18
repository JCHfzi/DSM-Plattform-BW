// TEst später Löschen
package de.dsm.data.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.dsm.data.dao.TimeseriesDAO;
import de.dsm.data.entity.Timeseries;

@Component
@Transactional
public class TimeseriesService {

	@Autowired
	private TimeseriesDAO timeseriesDao;

	public Timeseries getTimeseriesEntry(long flexPwrId) {
		return getTimeseriesDAO().findById(flexPwrId);
	}

	public Timeseries saveTimeseriesEntry(Timeseries entity) {
		return getTimeseriesDAO().saveOrUpdate(entity);
	}
	
	public Double getAverageValue(Long id, long intervalSize) {
		return getTimeseriesDAO().getAverageValue(id, intervalSize);
	}

	public List<Timeseries> getTimeseriesEntriesForPeriod(long flexPwrId, Date start, Date end) {
		return getTimeseriesDAO().findByFlexPwrIdAndPeriod(flexPwrId, start, end);
	}

	public List<Date> getPeriodForFlexibilityAndNoOfRecordsPeriod(long flexId, long noOfPointsToFetch) {
		return getTimeseriesDAO().findPeriodByFlexIdAndNoOfPoints(flexId, noOfPointsToFetch);
	}

	public TimeseriesDAO getTimeseriesDAO() {
		return timeseriesDao;
	}

	public void setTimeseriesDAO(TimeseriesDAO timeseriesDao) {
		this.timeseriesDao = timeseriesDao;
	}
}
