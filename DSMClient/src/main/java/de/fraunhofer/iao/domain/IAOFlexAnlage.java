package de.fraunhofer.iao.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Class represents a flexibility utitility and static characteristics.
 * 
 * @author dsm
 *
 */
public class IAOFlexAnlage {

	@JsonIgnore
	/**
	 * eindeutige FlexibilitätsId
	 */
	private String fId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Zeitpunkt t, zu dem die verschobene Energiemenge nachgeholt sein muss
	 */
	private String tShiftMax;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * verschiebbare Energiemenge
	 */
	private String eShift;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Schaltdauer, Zeitraum in der die Änderung der Bezugsliestung erfolgt
	 */
	private String dS;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Verschiebedauer, Teitraum in der die Verschiebung der Bezugsleistung
	 * nachgeholt werden muss
	 */
	private String dV;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * maximale Änderungsrate der Bezugsleistung
	 */
	private String deltaRampe;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Aktivierungsdauer; zeitlicher Vorlauf, der bis zur ersten geplanten
	 * Bezugsleistung notwendig ist
	 */
	private String dA;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Teilabruf von flexiblen Leistungen
	 */
	private String pTeilbar;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Wie viel Prozent im Jahr diese Umverteilung verfügbar ist
	 */
	private String verfügbarkeit;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	/**
	 * Wie oft diese Umverteilung aktivierbar ist innerhalb eines Jahres
	 */
	private String maxAktivierungen;

	private List<IAOFlexibility> flexibilities = new ArrayList<IAOFlexibility>();

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String gettShiftMax() {
		return tShiftMax;
	}

	public void settShiftMax(String tShiftMax) {
		this.tShiftMax = tShiftMax;
	}

	public String geteShift() {
		return eShift;
	}

	public void seteShift(String eShift) {
		this.eShift = eShift;
	}

	public String getdS() {
		return dS;
	}

	public void setdS(String dS) {
		this.dS = dS;
	}

	public String getdV() {
		return dV;
	}

	public void setdV(String dV) {
		this.dV = dV;
	}

	public String getDeltaRampe() {
		return deltaRampe;
	}

	public void setDeltaRampe(String deltaRampe) {
		this.deltaRampe = deltaRampe;
	}

	public String getdA() {
		return dA;
	}

	public void setdA(String dA) {
		this.dA = dA;
	}

	public String getpTeilbar() {
		return pTeilbar;
	}

	public void setpTeilbar(String pTeilbar) {
		this.pTeilbar = pTeilbar;
	}

	public String getVerfügbarkeit() {
		return verfügbarkeit;
	}

	public void setVerfügbarkeit(String verfügbarkeit) {
		this.verfügbarkeit = verfügbarkeit;
	}

	public String getMaxAktivierungen() {
		return maxAktivierungen;
	}

	public void setMaxAktivierungen(String maxAktivierungen) {
		this.maxAktivierungen = maxAktivierungen;
	}

	public List<IAOFlexibility> getFlexibilities() {
		return flexibilities;
	}

	public void setFlexibilities(List<IAOFlexibility> flexibilities) {
		this.flexibilities = flexibilities;
	}
}
