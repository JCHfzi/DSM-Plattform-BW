// Flexibilität, die auf der Website als Suchergebnis angezeigt wird

package de.dsm.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "FLEXIBILITY", uniqueConstraints = {
		@UniqueConstraint(columnNames = "FLEXID")})

public class Flexibility implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "FLEXID", unique = true, nullable = false)
	private Long id; //
	
	@Column(name = "FLEXLABEL", unique = false, nullable = true)
	private String label;

	@ManyToOne
	@JoinColumn(name="COMPID")
	private Company company;

	
	@Column(name = "P015P", unique = false, nullable = true)
	private Float p015p;
	
	@Column(name = "P015N", unique = false, nullable = true)
	private Float p015n;
	
	@Column(name = "P060P", unique = false, nullable = true)
	private Float p060p;
	
	@Column(name = "P060N", unique = false, nullable = true)
	private Float p060n;
	
	@Column(name = "P240P", unique = false, nullable = true)
	private Float p240p;
	
	@Column(name = "P240N", unique = false, nullable = true)
	private Float p240n;
	
	@Column(name = "DACTIVATION")
	private Float activationDuration;

	//Leistung
	@Column(name = "PMAX", unique = false, nullable = true)
	private Float posLeistung;
	@Column(name = "PMIN", unique = false, nullable = true)
	private Float negLeistung;
	
	//Stufe 1b
	@Column(name = "DELTARAMP", unique = false, nullable = true)
	private Float rampe; // maximale Änderungsrate der Bezugsleistung - DELTARAMP
	@Column(name = "PDIVISIBILITY", unique = false, nullable = true)
	private String teilbarkeit; // Teilabruf von flexiblen Leistungen möglich -PDIVISIBILITY
	@Column(name = "DACTIVATION", unique = false, nullable = true, insertable=false, updatable=false)
	private Float aktivierungsDauer; // Aktivierungsdauer; zeitlicher Vorlauf, der bis zur ersten geplanten Bezugsleistung notwendig ist - DACTIVATION


	// Optional
	@Column(name = "AVAILABILITY", unique = false, nullable = true)
	private BigDecimal verfuegbarkeit; // Wie viel Prozent im Jahr diese Umverteilung verfügbar ist - AVAILIBILITY
	@Column(name = "NACTIVATION", unique = false, nullable = true)
	private BigDecimal aktivierungen; // Wie oft diese Umverteilung aktivierbar ist innerhalb eines Jahres - NACTIVATION 
	@Column(name = "TSHIFTMAX", unique = false, nullable = true)
	private Date verschiebeZeitpunkt; // Zeitpunkt t, zu dem die verschobene Energiemenge nachgeholt sein muss - TSHIFTMAX
	@Column(name = "EMOVABLE", unique = false, nullable = true)
	private Float verschiebeEnergie; // verschiebbare Energiemenge - EMOVABLE
	@Column(name = "SWITCHINGDURATION", unique = false, nullable = true)
	private Float schaltDauer; //Schaltdauer; Zeitraum, in der die Änderung der Bezugsleistung erfolgt - SWITCHINGDURATION
	@Column(name = "MOVEDURATION", unique = false, nullable = true)
	private Float verschiebeDauer; //Verschiebedauer; Zeitraum, in dem die Verschiebung der Bezugsleistung nachgeholt wurde -MOVEDURATION

	@Column(name = "VOLTAGE_GROUP", unique = false, nullable = true)
	private String voltageGroup;
	@Column(name = "CONTROL_AREA", unique = false, nullable = true)
	private String controlArea;
	@Column(name = "NET_OPERATOR", unique = false, nullable = true)
	private String netOperator;

	
	@OneToMany(mappedBy="flexibility", fetch = FetchType.EAGER)
	private Set<FlexiblePower> flexiblePowers;

	
	public Flexibility() {
		
	}
	
	public Flexibility(Long fid, Company comp, Float posLeistung, Float negLeistung, Float rampe,
			String teilbarkeit, Float aktivierungsDauer, BigDecimal verfuegbarkeit, BigDecimal aktivierungen,
			Date verschiebeZeitpunkt, Float verschiebeEnergie, Float schaltDauer, Float verschiebeDauer) {

		this.id = fid;
		this.company = comp;
		this.posLeistung = posLeistung;
		this.negLeistung = negLeistung;
		this.rampe = rampe;
		this.teilbarkeit = teilbarkeit;
		this.aktivierungsDauer = aktivierungsDauer;
		this.verfuegbarkeit = verfuegbarkeit;
		this.aktivierungen = aktivierungen;
		this.verschiebeZeitpunkt = verschiebeZeitpunkt;
		this.verschiebeEnergie = verschiebeEnergie;
		this.schaltDauer = schaltDauer;
		this.verschiebeDauer = verschiebeDauer;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public Float getP015p() {
		return p015p;
	}


	public void setP015p(Float p015p) {
		this.p015p = p015p;
	}


	public Float getP015n() {
		return p015n;
	}


	public void setP015n(Float p015n) {
		this.p015n = p015n;
	}


	public Float getP060p() {
		return p060p;
	}


	public void setP060p(Float p060p) {
		this.p060p = p060p;
	}


	public Float getP060n() {
		return p060n;
	}


	public void setP060n(Float p060n) {
		this.p060n = p060n;
	}


	public Float getP240p() {
		return p240p;
	}


	public void setP240p(Float p240p) {
		this.p240p = p240p;
	}


	public Float getP240n() {
		return p240n;
	}


	public void setP240n(Float p240n) {
		this.p240n = p240n;
	}


	public Float getActivationDuration() {
		return activationDuration;
	}


	public void setActivationDuration(Float activationDuration) {
		this.activationDuration = activationDuration;
	}


	public Float getPosLeistung() {
		return posLeistung;
	}

	public void setPosLeistung(Float posLeistung) {
		this.posLeistung = posLeistung;
	}

	public Float getNegLeistung() {
		return negLeistung;
	}

	public void setNegLeistung(Float negLeistung) {
		this.negLeistung = negLeistung;
	}

	public Float getRampe() {
		return rampe;
	}

	public void setRampe(Float rampe) {
		this.rampe = rampe;
	}

	public String getTeilbarkeit() {
		return teilbarkeit;
	}

	public void setTeilbarkeit(String teilbarkeit) {
		this.teilbarkeit = teilbarkeit;
	}

	public Float getAktivierungsDauer() {
		return aktivierungsDauer;
	}

	public void setAktivierungsDauer(Float aktivierungsDauer) {
		this.aktivierungsDauer = aktivierungsDauer;
	}

	public BigDecimal getVerfuegbarkeit() {
		return verfuegbarkeit;
	}

	public void setVerfuegbarkeit(BigDecimal verfuegbarkeit) {
		this.verfuegbarkeit = verfuegbarkeit;
	}

	public BigDecimal getAktivierungen() {
		return aktivierungen;
	}

	public void setAktivierungen(BigDecimal aktivierungen) {
		this.aktivierungen = aktivierungen;
	}

	public Date getVerschiebeZeitpunkt() {
		return verschiebeZeitpunkt;
	}

	public void setVerschiebeZeitpunkt(Date verschiebeZeitpunkt) {
		this.verschiebeZeitpunkt = verschiebeZeitpunkt;
	}

	public Float getVerschiebeEnergie() {
		return verschiebeEnergie;
	}

	public void setVerschiebeEnergie(Float verschiebeEnergie) {
		this.verschiebeEnergie = verschiebeEnergie;
	}

	public Float getSchaltDauer() {
		return schaltDauer;
	}

	public void setSchaltDauer(Float schaltDauer) {
		this.schaltDauer = schaltDauer;
	}

	public Float getVerschiebeDauer() {
		return verschiebeDauer;
	}

	public void setVerschiebeDauer(Float verschiebeDauer) {
		this.verschiebeDauer = verschiebeDauer;
	}

	public Set<FlexiblePower> getFlexiblePowers() {
		return flexiblePowers;
	}

	public void setFlexiblePowers(Set<FlexiblePower> flexiblePowers) {
		this.flexiblePowers = flexiblePowers;
	}

	public FlexiblePower getFlexiblePowerById(BigDecimal flexPowerId) {
		for(FlexiblePower fp: getFlexiblePowers()) 
			if(fp.getSid().equals(flexPowerId))
				return fp;
		return null;
	}

	public FlexiblePower getFlexiblePowerByCategoryCode(String categoryCode) {
		for(FlexiblePower fp: getFlexiblePowers()) 
			if(fp.getCategoryCode().equals(categoryCode))
				return fp;
		return null;
	}
	
	public String getVoltageGroup() {
		return voltageGroup;
	}

	public void setVoltageGroup(String voltageGroup) {
		this.voltageGroup = voltageGroup;
	}

	public String getControlArea() {
		return controlArea;
	}

	public void setControlArea(String controlArea) {
		this.controlArea = controlArea;
	}

	public String getNetOperator() {
		return netOperator;
	}

	public void setNetOperator(String netOperator) {
		this.netOperator = netOperator;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
