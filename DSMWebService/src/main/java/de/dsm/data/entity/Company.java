package de.dsm.data.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANY")
public class Company {

	@Id
	@Column(name = "COMPID")
	private Long compId;
	
	@Column(name="COMPLABEL")
	private String companyLabel;

	@Column(name="BRANCHE")
	private String industry;

	//STREET	VARCHAR2(255 CHAR)
	@Column(name="STREET")
	private String street;

	//HOUSENUMBER	VARCHAR2(255 CHAR)
	@Column(name="HOUSENUMBER")
	private String houseNo;
	
	//PLZ	VARCHAR2(255 CHAR)
	@Column(name="PLZ")
	private String zipCode;

	//CITY	VARCHAR2(255 CHAR)
	@Column(name="CITY")
	private String city;

	//LONGITUDE	FLOAT(126)
	@Column(name="LONGITUDE")
	private Float longitude;

	//LATITUDE	FLOAT(126)
	@Column(name="LATITUDE")
	private Float latitude;
	
	//FORENAME	VARCHAR2(255 CHAR)
	@Column(name="FORENAME")
	private String forename;

	//SIRNAME	VARCHAR2(255 CHAR)
	@Column(name="SIRNAME")
	private String sirname;

	//MAIL	VARCHAR2(255 CHAR)
	@Column(name="MAIL")
	private String eMailAddress;

	//TELNUMBER	VARCHAR2(255 CHAR)
	@Column(name="TELNUMBER")
	private String phoneNumber;

	public Company() {
		
	}
	
	public Company(Long cid, String cName, String industry, String street, String houseNo,
			String zip, String city, Float longitude, Float latitude,
			String forename, String sirname, String eMail, String phone) {

		this.compId = cid;
		this.companyLabel = cName;
		this.industry = industry;
		this.street = street;
		this.houseNo = houseNo;
		this.zipCode = zip;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.forename = forename;
		this.sirname = sirname;
		this.eMailAddress = eMail;
		this.phoneNumber = phone;
	}
	
	public Long getCompId() {
		return compId;
	}

	public void setCompId(Long compId) {
		this.compId = compId;
	}

	public String getCompanyLabel() {
		return companyLabel;
	}

	public void setCompanyLabel(String companyLabel) {
		this.companyLabel = companyLabel;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSirname() {
		return sirname;
	}

	public void setSirname(String sirname) {
		this.sirname = sirname;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
