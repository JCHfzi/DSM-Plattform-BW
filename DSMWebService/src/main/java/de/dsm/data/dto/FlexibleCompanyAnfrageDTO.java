// FilterEingabe in View

package de.dsm.data.dto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="flexibleCompanyAnfrageDTO")
@ViewScoped
public class FlexibleCompanyAnfrageDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private FahrPlanAnfrageDTO flexibleCompanyAnfrageDTO;
	
// Attributes
/*durchschnittlich verfügbare Leistung (pro Zeitintervall)								
bestimmte Schaltdauern: P_+,-_1/4h,1h,4h								
Anschluss-Spannungsebene 								
Rampe + Aktivierungsvorlauf (<5min/ <15min/…)								
Minimale Schaltdauer								
Maximale Schaltdauer								
Max. Aktivierungsdauer								
Umgebungstemperaturen (Winter/ Frühling+Herbst/ Sommer)								
Tageszeit (Tag/ Nacht)								
Wochentag (Mo-Fr)/ Wochenende (Sa-So)								
*/
//Meta
public String compName; //
public String compAdresse; //
public String regelzone; //
public String spannungsebene; //
public String geoLong; // LONGITUDE
public String geoLat; // LATITUDE

//Leistung
public Integer posMinLeistung; //neg flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015P
public Integer posMaxLeistung; //positive flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015P
public Integer negMinLeistung; //neg flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015N
public Integer negMaxLeistung; //positive flexible Leistung, die über eine viertel Stunde hinweg bereitgestellt werden kann - P015N

//Stufe 1b
public Integer minRampe; // maximale Änderungsrate der Bezugsleistung - DELTARAMP
public String teilbarkeit; // Teilabruf von flexiblen Leistungen möglich -PDIVISIBILITY
public Integer maxAktivierungsdauer; // Aktivierungsdauer; zeitlicher Vorlauf, der bis zur ersten geplanten Bezugsleistung notwendig ist - DACTIVATION


// Optionall
public String minVerfuegbarkeit; // Wie viel Prozent im Jahr diese Umverteilung verfügbar ist - AVAILIBILITY
public String minAktivierungen; // Wie oft diese Umverteilung aktivierbar ist innerhalb eines Jahres - NACTIVATION 
public String minVerschiebeZeitpunkt; // Zeitpunkt t, zu dem die verschobene Energiemenge nachgeholt sein muss - TSHIFTMAX
public String minVerschiebeEnergie; // verschiebbare Energiemenge - EMOVABLE
public String minSchaltDauer; //Schaltdauer; Zeitraum, in der die Änderung der Bezugsleistung erfolgt - SWITCHINGDURATION
public String minVerschiebeDauer; //Verschiebedauer; Zeitraum, in dem die Verschiebung der Bezugsleistung nachgeholt wurde -MOVEDURATION


//Getter Setter
public FahrPlanAnfrageDTO getFlexibleCompanyAnfrageDTO() {
	return flexibleCompanyAnfrageDTO;
}
public void setFlexibleCompanyAnfrageDTO(FahrPlanAnfrageDTO flexibleCompanyAnfrageDTO) {
	this.flexibleCompanyAnfrageDTO = flexibleCompanyAnfrageDTO;
}
public String getCompName() {
	return compName;
}
public void setCompName(String compName) {
	this.compName = compName;
}
public String getCompAdresse() {
	return compAdresse;
}
public void setCompAdresse(String compAdresse) {
	this.compAdresse = compAdresse;
}
public String getRegelzone() {
	return regelzone;
}
public void setRegelzone(String regelzone) {
	this.regelzone = regelzone;
}
public String getSpannungsebene() {
	return spannungsebene;
}
public void setSpannungsebene(String spannungsebene) {
	this.spannungsebene = spannungsebene;
}
public String getGeoLong() {
	return geoLong;
}
public void setGeoLong(String geoLong) {
	this.geoLong = geoLong;
}
public String getGeoLat() {
	return geoLat;
}
public void setGeoLat(String geoLat) {
	this.geoLat = geoLat;
}
public Integer getPosMinLeistung() {
	return posMinLeistung;
}
public void setPosMinLeistung(Integer posMinLeistung) {
	this.posMinLeistung = posMinLeistung;
}
public Integer getPosMaxLeistung() {
	return posMaxLeistung;
}
public void setPosMaxLeistung(Integer posMaxLeistung) {
	this.posMaxLeistung = posMaxLeistung;
}
public Integer getNegMinLeistung() {
	return negMinLeistung;
}
public void setNegMinLeistung(Integer negMinLeistung) {
	this.negMinLeistung = negMinLeistung;
}
public Integer getNegMaxLeistung() {
	return negMaxLeistung;
}
public void setNegMaxLeistung(Integer negMaxLeistung) {
	this.negMaxLeistung = negMaxLeistung;
}
public Integer getMinRampe() {
	return minRampe;
}
public void setMinRampe(Integer minRampe) {
	this.minRampe = minRampe;
}
public String getTeilbarkeit() {
	return teilbarkeit;
}
public void setTeilbarkeit(String teilbarkeit) {
	this.teilbarkeit = teilbarkeit;
}
public Integer getMaxAktivierungsdauer() {
	return maxAktivierungsdauer;
}
public void setMaxAktivierungsdauer(Integer maxAktivierungsdauer) {
	this.maxAktivierungsdauer = maxAktivierungsdauer;
}
public String getMinVerfuegbarkeit() {
	return minVerfuegbarkeit;
}
public void setMinVerfuegbarkeit(String minVerfuegbarkeit) {
	this.minVerfuegbarkeit = minVerfuegbarkeit;
}
public String getMinAktivierungen() {
	return minAktivierungen;
}
public void setMinAktivierungen(String minAktivierungen) {
	this.minAktivierungen = minAktivierungen;
}
public String getMinVerschiebeZeitpunkt() {
	return minVerschiebeZeitpunkt;
}
public void setMinVerschiebeZeitpunkt(String minVerschiebeZeitpunkt) {
	this.minVerschiebeZeitpunkt = minVerschiebeZeitpunkt;
}
public String getMinVerschiebeEnergie() {
	return minVerschiebeEnergie;
}
public void setMinVerschiebeEnergie(String minVerschiebeEnergie) {
	this.minVerschiebeEnergie = minVerschiebeEnergie;
}
public String getMinSchaltDauer() {
	return minSchaltDauer;
}
public void setMinSchaltDauer(String minSchaltDauer) {
	this.minSchaltDauer = minSchaltDauer;
}
public String getMinVerschiebeDauer() {
	return minVerschiebeDauer;
}
public void setMinVerschiebeDauer(String minVerschiebeDauer) {
	this.minVerschiebeDauer = minVerschiebeDauer;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}


	
// Constructor
public FlexibleCompanyAnfrageDTO(String compName, String compAdresse, String regelzone,
		String geoLong, String geoLat, Integer posMinLeistung, Integer posMaxLeistung, Integer negMinLeistung,
		Integer negMaxLeistung, Integer minRampe, String teilbarkeit, Integer maxAktivierungsdauer) {
	super();
	this.compName = compName;
	this.compAdresse = compAdresse;
	this.regelzone = regelzone;
	this.geoLong = geoLong;
	this.geoLat = geoLat;
	this.posMinLeistung = posMinLeistung;
	this.posMaxLeistung = posMaxLeistung;
	this.negMinLeistung = negMinLeistung;
	this.negMaxLeistung = negMaxLeistung;
	this.minRampe = minRampe;
	this.teilbarkeit = teilbarkeit;
	this.maxAktivierungsdauer = maxAktivierungsdauer;
}	
	
	
}
