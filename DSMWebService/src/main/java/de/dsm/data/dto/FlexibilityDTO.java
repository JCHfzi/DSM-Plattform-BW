//ALT - Bald Löschen
package de.dsm.data.dto;

public class FlexibilityDTO {

	public FlexibilityDTO()
	{
		
	}
	
	public FlexibilityDTO(String id, String company, int price, boolean soldState, String icon) {
		super();
		this.id = id;
		this.company = company;
		this.price = price;
		this.soldState = soldState;
		this.icon = icon;
		
	}
	
	
	// Aus View FLEXIBLE_POWER
	private int SID	;
	private String CATEGORY;
	private float PMEAN	;
	private int FLEXID;
	private String FLEXLABEL;
	private float  P015P;
	private float  P015N;
	private float P060P;
	private float P060N;
	private float P240P;
	private float P240N;
	private float DELTARAMP;
	private float DACTIVATION;
	private String PDIVISIBILITY;
	private int AVAILABILITY;
	private int NACTIVATION;
	private float  PMAX;
	private float  PMIN;
	private String TSHIFTMAX;
	private float  EMOVABLE;
	private float  SWITCHINGDURATION;
	private float MOVEDURATION;
	private int COMPID;
	private String COMPLABEL;
	private String BRANCHE;
	
	public FlexibilityDTO(int sID, String cATEGORY, float pMEAN, int fLEXID, String fLEXLABEL, float p015p, float p015n,
			float p060p, float p060n, float p240p, float p240n, float dELTARAMP, float dACTIVATION,
			String pDIVISIBILITY, int aVAILABILITY, int nACTIVATION, float pMAX, float pMIN, String tSHIFTMAX,
			float eMOVABLE, float sWITCHINGDURATION, float mOVEDURATION, int cOMPID, String cOMPLABEL, String bRANCHE,
			String sTREET, String hOUSENUMBER, String pLZ, String cITY, float lONGITUDE, float lATITUDE,
			String fORENAME, String sIRNAME, String mAIL, String tELNUMBER) {
		super();
		SID = sID;
		CATEGORY = cATEGORY;
		PMEAN = pMEAN;
		FLEXID = fLEXID;
		FLEXLABEL = fLEXLABEL;
		P015P = p015p;
		P015N = p015n;
		P060P = p060p;
		P060N = p060n;
		P240P = p240p;
		P240N = p240n;
		DELTARAMP = dELTARAMP;
		DACTIVATION = dACTIVATION;
		PDIVISIBILITY = pDIVISIBILITY;
		AVAILABILITY = aVAILABILITY;
		NACTIVATION = nACTIVATION;
		PMAX = pMAX;
		PMIN = pMIN;
		TSHIFTMAX = tSHIFTMAX;
		EMOVABLE = eMOVABLE;
		SWITCHINGDURATION = sWITCHINGDURATION;
		MOVEDURATION = mOVEDURATION;
		COMPID = cOMPID;
		COMPLABEL = cOMPLABEL;
		BRANCHE = bRANCHE;
		STREET = sTREET;
		HOUSENUMBER = hOUSENUMBER;
		PLZ = pLZ;
		CITY = cITY;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		FORENAME = fORENAME;
		SIRNAME = sIRNAME;
		MAIL = mAIL;
		TELNUMBER = tELNUMBER;
	}

	public FlexibilityDTO(int sID, String cATEGORY, float pMEAN, int fLEXID, String fLEXLABEL, float p015p, float p015n,
			float p060p, float p060n, float p240p, float p240n, float dELTARAMP, float dACTIVATION,
			String pDIVISIBILITY, int aVAILABILITY, int nACTIVATION, float pMAX, float pMIN, String tSHIFTMAX,
			float eMOVABLE, float sWITCHINGDURATION, float mOVEDURATION, int cOMPID, String cOMPLABEL, String bRANCHE,
			String sTREET, String hOUSENUMBER, String pLZ, String cITY, float lONGITUDE, float lATITUDE,
			String fORENAME, String sIRNAME, String mAIL, String tELNUMBER, String id, String company, int price,
			boolean soldState, String icon) {
		super();
		SID = sID;
		CATEGORY = cATEGORY;
		PMEAN = pMEAN;
		FLEXID = fLEXID;
		FLEXLABEL = fLEXLABEL;
		P015P = p015p;
		P015N = p015n;
		P060P = p060p;
		P060N = p060n;
		P240P = p240p;
		P240N = p240n;
		DELTARAMP = dELTARAMP;
		DACTIVATION = dACTIVATION;
		PDIVISIBILITY = pDIVISIBILITY;
		AVAILABILITY = aVAILABILITY;
		NACTIVATION = nACTIVATION;
		PMAX = pMAX;
		PMIN = pMIN;
		TSHIFTMAX = tSHIFTMAX;
		EMOVABLE = eMOVABLE;
		SWITCHINGDURATION = sWITCHINGDURATION;
		MOVEDURATION = mOVEDURATION;
		COMPID = cOMPID;
		COMPLABEL = cOMPLABEL;
		BRANCHE = bRANCHE;
		STREET = sTREET;
		HOUSENUMBER = hOUSENUMBER;
		PLZ = pLZ;
		CITY = cITY;
		LONGITUDE = lONGITUDE;
		LATITUDE = lATITUDE;
		FORENAME = fORENAME;
		SIRNAME = sIRNAME;
		MAIL = mAIL;
		TELNUMBER = tELNUMBER;
		this.id = id;
		this.company = company;
		this.price = price;
		this.soldState = soldState;
		this.icon = icon;
	}

	public int getSID() {
		return SID;
	}

	public void setSID(int sID) {
		SID = sID;
	}

	public String getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}

	public float getPMEAN() {
		return PMEAN;
	}

	public void setPMEAN(float pMEAN) {
		PMEAN = pMEAN;
	}

	public int getFLEXID() {
		return FLEXID;
	}

	public void setFLEXID(int fLEXID) {
		FLEXID = fLEXID;
	}

	public String getFLEXLABEL() {
		return FLEXLABEL;
	}

	public void setFLEXLABEL(String fLEXLABEL) {
		FLEXLABEL = fLEXLABEL;
	}

	public float getP015P() {
		return P015P;
	}

	public void setP015P(float p015p) {
		P015P = p015p;
	}

	public float getP015N() {
		return P015N;
	}

	public void setP015N(float p015n) {
		P015N = p015n;
	}

	public float getP060P() {
		return P060P;
	}

	public void setP060P(float p060p) {
		P060P = p060p;
	}

	public float getP060N() {
		return P060N;
	}

	public void setP060N(float p060n) {
		P060N = p060n;
	}

	public float getP240P() {
		return P240P;
	}

	public void setP240P(float p240p) {
		P240P = p240p;
	}

	public float getP240N() {
		return P240N;
	}

	public void setP240N(float p240n) {
		P240N = p240n;
	}

	public float getDELTARAMP() {
		return DELTARAMP;
	}

	public void setDELTARAMP(float dELTARAMP) {
		DELTARAMP = dELTARAMP;
	}

	public float getDACTIVATION() {
		return DACTIVATION;
	}

	public void setDACTIVATION(float dACTIVATION) {
		DACTIVATION = dACTIVATION;
	}

	public String getPDIVISIBILITY() {
		return PDIVISIBILITY;
	}

	public void setPDIVISIBILITY(String pDIVISIBILITY) {
		PDIVISIBILITY = pDIVISIBILITY;
	}

	public int getAVAILABILITY() {
		return AVAILABILITY;
	}

	public void setAVAILABILITY(int aVAILABILITY) {
		AVAILABILITY = aVAILABILITY;
	}

	public int getNACTIVATION() {
		return NACTIVATION;
	}

	public void setNACTIVATION(int nACTIVATION) {
		NACTIVATION = nACTIVATION;
	}

	public float getPMAX() {
		return PMAX;
	}

	public void setPMAX(float pMAX) {
		PMAX = pMAX;
	}

	public float getPMIN() {
		return PMIN;
	}

	public void setPMIN(float pMIN) {
		PMIN = pMIN;
	}

	public String getTSHIFTMAX() {
		return TSHIFTMAX;
	}

	public void setTSHIFTMAX(String tSHIFTMAX) {
		TSHIFTMAX = tSHIFTMAX;
	}

	public float getEMOVABLE() {
		return EMOVABLE;
	}

	public void setEMOVABLE(float eMOVABLE) {
		EMOVABLE = eMOVABLE;
	}

	public float getSWITCHINGDURATION() {
		return SWITCHINGDURATION;
	}

	public void setSWITCHINGDURATION(float sWITCHINGDURATION) {
		SWITCHINGDURATION = sWITCHINGDURATION;
	}

	public float getMOVEDURATION() {
		return MOVEDURATION;
	}

	public void setMOVEDURATION(float mOVEDURATION) {
		MOVEDURATION = mOVEDURATION;
	}

	public int getCOMPID() {
		return COMPID;
	}

	public void setCOMPID(int cOMPID) {
		COMPID = cOMPID;
	}

	public String getCOMPLABEL() {
		return COMPLABEL;
	}

	public void setCOMPLABEL(String cOMPLABEL) {
		COMPLABEL = cOMPLABEL;
	}

	public String getBRANCHE() {
		return BRANCHE;
	}

	public void setBRANCHE(String bRANCHE) {
		BRANCHE = bRANCHE;
	}

	public String getSTREET() {
		return STREET;
	}

	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}

	public String getHOUSENUMBER() {
		return HOUSENUMBER;
	}

	public void setHOUSENUMBER(String hOUSENUMBER) {
		HOUSENUMBER = hOUSENUMBER;
	}

	public String getPLZ() {
		return PLZ;
	}

	public void setPLZ(String pLZ) {
		PLZ = pLZ;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public float getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(float lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

	public float getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(float lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public String getFORENAME() {
		return FORENAME;
	}

	public void setFORENAME(String fORENAME) {
		FORENAME = fORENAME;
	}

	public String getSIRNAME() {
		return SIRNAME;
	}

	public void setSIRNAME(String sIRNAME) {
		SIRNAME = sIRNAME;
	}

	public String getMAIL() {
		return MAIL;
	}

	public void setMAIL(String mAIL) {
		MAIL = mAIL;
	}

	public String getTELNUMBER() {
		return TELNUMBER;
	}

	public void setTELNUMBER(String tELNUMBER) {
		TELNUMBER = tELNUMBER;
	}

	public void setSoldState(boolean soldState) {
		this.soldState = soldState;
	}


	private String STREET;
	private String HOUSENUMBER;
	private String PLZ;
	private String CITY;
	private float LONGITUDE;
	private float  LATITUDE;
	private String FORENAME;
	private String SIRNAME;
	private String MAIL;
	private String TELNUMBER;
	
	//Alte Beispiele
	private String id;
	private String company;
	private int price;
	private boolean soldState;
	private String icon;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isSoldState() {
		return soldState;
	}

	public void setRandomSoldState(boolean soldState) {
		this.soldState = soldState;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
