package de.dsm.data.dao;

import java.util.Date;
import java.util.List;

import de.dsm.data.entity.Timeseries;

public interface TimeseriesDAO {
	public Timeseries findById(Long id);
	
	public List<Timeseries> findByFlexPwrIdAndPeriod(Long fpId, Date start, Date end);
	
	public Timeseries saveOrUpdate(final Timeseries entity);

	public Double getAverageValue(Long id, long intervalSize);
	
	public List<Date> findPeriodByFlexIdAndNoOfPoints(Long flexId, long noOfPointsToFetch);
}
