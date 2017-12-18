package de.dsm.data.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIMESERIES")
public class Timeseries {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_ID_GEN")
	@SequenceGenerator(name="TS_ID_GEN", sequenceName="TIMESERIES_SEQ", allocationSize=1)
	@Column(name = "ID")
	private BigDecimal id;
	
	@ManyToOne
	@JoinColumn(name="SID")
	private FlexiblePower flexiblePower;

	@Column(name = "TS1")
	private Date start; 

	@Column(name = "TS2")
	private Date end; 
	
	@Column(name = "VALUE")
	private BigDecimal value;

	public Timeseries() {
		
	}
	
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}


	public FlexiblePower getFlexiblePower() {
		return flexiblePower;
	}

	public void setFlexiblePower(FlexiblePower flexiblePower) {
		this.flexiblePower = flexiblePower;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public BigDecimal getValue() {
		return value;
	}

	public BigDecimal getSignedValue() {
		if(this.getFlexiblePower().getCategoryCode().endsWith("n")) {
			return value.negate();
		} else {
			return value;
		}
	}
	
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public Date getMedianTS() {
		if(getStart() != null && getEnd() != null) {
			long medianTS = getEnd().getTime() - (getEnd().getTime() - getStart().getTime())/2;
			return new Date(medianTS);
		} else {
			return getStart()!=null?getStart():getEnd();
		}
			
	}

	public String getMedianTSasString() {
		Date medianTs = this.getMedianTS();
		if(medianTs != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			return sdf.format(medianTs);
		} else
			return "";
			
	}

}
