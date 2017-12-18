package de.fzi.domain;

//import java.sql.Time;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;


//Datenpunkt, wie er vom Flughafen Stuttgart ausgegeben wird
public class DatenPunkt {



        private Date Datum;
        private String Uhrzeit;
        private String KomischerBuchstabe;
        private String ID;
        private String Beschreibung;
        private String Uid;
        private String Fid;  
		private String Wert;
        private String Einheit;       
        private String Leer;
        
        
        public String getUhrzeit() {
			return Uhrzeit;
		}
		public void setUhrzeit(String uhrzeit) {
			Uhrzeit = uhrzeit;
		}

        public Date getDatum() {
			return Datum;
		}
		public void setDatum(Date datum) {
			Datum = datum;
		}
		public String getKomischerBuchstabe() {
			return KomischerBuchstabe;
		}
		public void setKomischerBuchstabe(String komischerBuchstabe) {
			KomischerBuchstabe = komischerBuchstabe;
		}
		public String getID() {
			return ID;
		}
		public void setID(String iD) {
			ID = iD;
		}
		public String getBeschreibung() {
			return Beschreibung;
		}
		public void setBeschreibung(String beschreibung) {
			Beschreibung = beschreibung;
		}
		public String getWert() {
			return Wert;
		}
		public void setWert(String wert) {
			Wert = wert;
		}
		public String getEinheit() {
			return Einheit;
		}
		public void setEinheit(String einheit) {
			Einheit = einheit;
		}

		public void transformTo(String einheit) {
			Einheit = einheit;
		}   

		public String getUid() {
			return Uid;
		}
		public void setUid(String uid) {
			Uid = uid;
		}
		public String getFid() {
			return Fid;
		}
		public void setFid(String fid) {
			Fid = fid;
		}

		//ALT Methode die Anhand einer Property Datei eine Flexibilität zu einem Datenpunkt zurückgibt
		public Flexibility toFlexibility(Properties prop){
			Flexibility Flex = new Flexibility();
			
			Flex.setpPlus_1(0.0);
			Flex.setpPlus_1_4(0.0);
			Flex.setpPlus_4(0.0);
			Flex.setpMinus_1(0.0);
			Flex.setpMinus_1_4(0.0);
			Flex.setpMinus_4(0.0);
			
			
			Flex.setUid(this.Uid);
			
         
	    	for (String value : getPropertyList(prop, "^Verbraucher"))
	    	{
	    	    System.out.println(value);
	    	}
			
			// Übernehme Min und max werte aus Propery
			Double pMin =0.0;
			Double pMax =1000.0;
			
			// Finde Typ aus Property
			String Typ="AbLa";
			// Finde Verschiebehorizont aus Property
			String Horizont="1";
			
			if (Typ=="AbLa"){
				
				if (Horizont=="4") {
				Flex.setpMinus_4(Double.parseDouble(this.Wert));
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont=="1") {
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont=="1_4") {
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				
				Flex.setpPlus_1(0.0);
				Flex.setpPlus_1_4(0.0);
				Flex.setpPlus_4(0.0);
				
				
			}
			else if (Typ=="Erz"){
				
				if (Horizont=="4") {
				Flex.setpMinus_4(Double.parseDouble(this.Wert));
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont=="1") {
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont=="1_4") {
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				
				Flex.setpPlus_1(0.0);
				Flex.setpPlus_1_4(0.0);
				Flex.setpPlus_4(0.0);
				
				
			}
			
			else {
				
				if (Horizont=="4") {
					Flex.setpMinus_4(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1_4(Double.parseDouble(this.Wert)-pMin);
					
					Flex.setpPlus_4(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				}
				
				if (Horizont=="1") {
					Flex.setpMinus_1(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1_4(Double.parseDouble(this.Wert)-pMin);
					
					Flex.setpPlus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				
				}
				
				if (Horizont=="1_4") {
					Flex.setpMinus_1_4(pMax-Double.parseDouble(this.Wert)-pMin);
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				}
				
				
			}
			
			
			
			
			
		return Flex;
		}
		
		
		// Methode die Anhand der Informationen eines Unternehmen eine Flexibilität zu einem Datenpunkt zurückgibt.
		// Methode anpassen
		public Flexibility toFlexibility(FZICompany comp){
			
			Flexibility Flex = new Flexibility();
			FlexAnlage ThisFlexAnlage = null;
			Double pMin = 0.0;
			Double pMax= 0.0;
			
			Flex.setUid(comp.getuId());
			Flex.setFid(this.ID);
			
			
			// Setzte TS
			// 2017-04-20T13:37:07.789Z
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String reportDate = df.format(this.getDatum());
			
			// Beispiel TimeStamp IAO 2017-04-20T13:39:08.174Z
			
			Flex.setTS1(reportDate + "T" + this.getUhrzeit()+".000Z");
			Flex.setTS2(reportDate + "T" + this.getUhrzeit()+".000Z");			

			
	      	for (FlexAnlage temp : comp.getAnlagen()) {
	      		
				if (this.getID().equals(temp.getFid()))
					{
	      			
	      			ThisFlexAnlage = temp;
					//System.out.println(temp.getFid());
					pMin =ThisFlexAnlage.getpMin();
					pMax =ThisFlexAnlage.getpMax();
				} 
//					else {
//					return null;
//					
//					}
				
				
	    	}
	      	
	      	if (ThisFlexAnlage==null) {
	      		return null;
	      	}
			
			Flex.setpPlus_1(0.0);
			Flex.setpPlus_1_4(0.0);
			Flex.setpPlus_4(0.0);
			Flex.setpMinus_1(0.0);
			Flex.setpMinus_1_4(0.0);
			Flex.setpMinus_4(0.0);
			

		

			





			Double Horizont=ThisFlexAnlage.getVerschiebeHorizont();
			
			if (ThisFlexAnlage.getTyp().equals("AbLa")){
				
				if (Horizont==4.0) {
				Flex.setpPlus_4(Double.parseDouble(this.Wert));
				Flex.setpPlus_1(Double.parseDouble(this.Wert));
				Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont==1.0) {
				Flex.setpPlus_1(Double.parseDouble(this.Wert));
				Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont==0.25) {
				Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				}
				
				
				Flex.setpMinus_1(0.0);
				Flex.setpMinus_1_4(0.0);
				Flex.setpMinus_4(0.0);
				
				
			} 
			
			else if (ThisFlexAnlage.getTyp().equals("PV")){
				
				if (Horizont==4.0) {
				Flex.setpMinus_4(Double.parseDouble(this.Wert));
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont==1.0) {
				Flex.setpMinus_1(Double.parseDouble(this.Wert));
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont==0.25) {
				Flex.setpMinus_1_4(Double.parseDouble(this.Wert));
				}
				
				
				Flex.setpPlus_1(0.0);
				Flex.setpPlus_1_4(0.0);
				Flex.setpPlus_4(0.0);
				
				
			} 
			
			else if (ThisFlexAnlage.getTyp().equals("VariablerVerbraucher")){
				
				if (Horizont==4.0) {
					Flex.setpMinus_4(pMax-Double.parseDouble(this.Wert));
					Flex.setpMinus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpMinus_1_4(pMax-Double.parseDouble(this.Wert));
					
					Flex.setpPlus_4(Double.parseDouble(this.Wert));
					Flex.setpPlus_1(Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				}
				
				if (Horizont==1.0) {
					Flex.setpMinus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpMinus_1_4(pMax-Double.parseDouble(this.Wert));
					
					Flex.setpPlus_1(Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				
				}
				
				if (Horizont==0.25) {
					Flex.setpMinus_1_4(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(Double.parseDouble(this.Wert));
				}
			
				
				
			} 
			
			else {
				
				if (Horizont==4.0) {
					Flex.setpMinus_4(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1_4(Double.parseDouble(this.Wert)-pMin);
					
					Flex.setpPlus_4(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				}
				
				if (Horizont==1.0) {
					Flex.setpMinus_1(Double.parseDouble(this.Wert)-pMin);
					Flex.setpMinus_1_4(Double.parseDouble(this.Wert)-pMin);
					
					Flex.setpPlus_1(pMax-Double.parseDouble(this.Wert));
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				
				}
				
				if (Horizont==0.25) {
					Flex.setpMinus_1_4(pMax-Double.parseDouble(this.Wert)-pMin);
					Flex.setpPlus_1_4(pMax-Double.parseDouble(this.Wert));
				}
				
				
			}
			
			
			
			
			
		return Flex;
		}
		//
		
    	public static List<String> getPropertyList(Properties properties, String name)    	{
    	    List<String> result = new ArrayList<String>();
    	    for (Map.Entry<Object, Object> entry : properties.entrySet())
    	    {
    	        if (((String)entry.getKey()).matches("^" + Pattern.quote(name) + "\\.\\d+$"))
    	        {
    	            result.add((String) entry.getValue());
    	        }
    	    }
    	    return result;
    	}
		public String getLeer() {
			return Leer;
		}
		public void setLeer(String leer) {
			Leer = leer;
		}
}
