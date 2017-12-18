// In Arbeit - Gibt Pfad des passenden Fahrplan aus


package de.dsm.client.service;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import de.dsm.data.dto.FahrPlanAnfrageDTO;

@ManagedBean(name = "fahrplanService")
@ViewScoped
public class FahrplanService {
		
//Irgendein Objekt, das die hinterhelgen Fahrpläne speichert

public List<FahrPlanAnfrageDTO> fertigeFahrplaene = new ArrayList<FahrPlanAnfrageDTO>();

	
//Methode die Anfrage erhält und Pfad zurückgibt
public String getFahrplanPath(FahrPlanAnfrageDTO Anfrage){

String answer;

answer = "/resources/images/fahrplaene/KeinFahrplan.png";

if  (Anfrage.jahreszeit==null || Anfrage.witterung==null){
	return answer;	
}

if  (Anfrage.gesamtleistung.equalsIgnoreCase("-5000")){
	
	if (Anfrage.jahreszeit.equalsIgnoreCase("Winter") && Anfrage.witterung.equalsIgnoreCase("bewölkt") ) {
		answer= "/resources/images/fahrplaene/FlexFahrplan_KaltWolkig_NegSRL_23012016_1200_1245_final.png";
		
	}else if (Anfrage.jahreszeit.equalsIgnoreCase("Sommer") && Anfrage.witterung.equalsIgnoreCase("bewölkt") ){
		answer =  "/resources/images/fahrplaene/FlexFahrplan_WarmWolkig_NegSRL_04092016_1200_1245_final.png";
	
	}else if (Anfrage.jahreszeit.equalsIgnoreCase("Winter") && Anfrage.witterung.equalsIgnoreCase("sonnig") ){
		answer =  "/resources/images/fahrplaene/FlexFahrplan_KaltSonnig_NegSRL_10032016_1200_1245_final.png";
		
	}else if (Anfrage.jahreszeit.equalsIgnoreCase("Sommer") && Anfrage.witterung.equalsIgnoreCase("sonnig") ){
		answer =  "/resources/images/fahrplaene/FlexFahrplan_WarmSonnig_NegSRL_04092016_1200_1245_final.png";
	}else {
		answer = "/resources/images/fahrplaene/KeinFahrplan.png";
	}
	
	}else{
	
		if (Anfrage.jahreszeit.equalsIgnoreCase("Winter") && Anfrage.witterung.equalsIgnoreCase("bewölkt") ) {
			answer= "/resources/images/fahrplaene/FlexFahrplan_KaltWolkig_PosSRL_23012016_1200_1245_final.png";
			
		}else if (Anfrage.jahreszeit.equalsIgnoreCase("Sommer") && Anfrage.witterung.equalsIgnoreCase("bewölkt") ){
			answer =  "/resources/images/fahrplaene/FlexFahrplan_WarmWolkig_PosSRL_04092016_1200_1245_final.png";
		
		}else if (Anfrage.jahreszeit.equalsIgnoreCase("Winter") && Anfrage.witterung.equalsIgnoreCase("sonnig") ){
			answer =  "/resources/images/fahrplaene/FlexFahrplan_KaltSonnig_PosSRL_10032016_1200_1245_final.png";
			
		}else if (Anfrage.jahreszeit.equalsIgnoreCase("Sommer") && Anfrage.witterung.equalsIgnoreCase("sonnig") ){
			answer =  "/resources/images/fahrplaene/FlexFahrplan_WarmSonnig_PosSRL_04092016_1200_1245_final.png";
		}else {
			answer = "/resources/images/fahrplaene/KeinFahrplan.png";
		}
}

	
	
return answer;	
	
	/*
	fertigeFahrplaene.clear();
	//fertigeFahrplaene.add(new FahrPlanAnfrageDTO(startzeit, vermarktungsoption, gesamtleistung, aktivierungsdauer, umgebungstemperatur,  jahreszeit,  witterung));
	fertigeFahrplaene.add(new FahrPlanAnfrageDTO(null, "Select One", "Select One", "Select One", "Select One",  "Select One",  "Select One"));
	fertigeFahrplaene.add(new FahrPlanAnfrageDTO(null, "PRL", "Select One", "Select One", "Select One",  "Select One",  "Select One"));
	fertigeFahrplaene.add(new FahrPlanAnfrageDTO(null, "SRL", "Select One", "Select One", "Select One",  "Select One",  "Select One"));
	fertigeFahrplaene.add(new FahrPlanAnfrageDTO(null, "MR", "Select One", "Select One", "Select One",  "Select One",  "Select One"));
		
	int count =0;
	System.out.println(Anfrage.vermarktungsoption);
	
	for (FahrPlanAnfrageDTO element : fertigeFahrplaene) {
	    // 1 - can call methods of element
		//if (fertigeFahrplaene.get(0).equals(fertigeFahrplaene.get(0))) {
		if (element.equals(Anfrage)) {
			System.out.println(count);
		break;
			
			}
		count = count+1;
	}
	
	//Wenn null Problem
	if (Anfrage.equals(null)) {
		count=0;
	}
	
	
	//Anfrage.getVermarktungsoption().equalsIgnoreCase("Select One")

		
    	
		//Keine vollständige Eingabe
		if (count==0) {
			return "/resources/images/kaleici.png";
		//Sonst Zeige Bild mit Hinweis: "Leider kein passender Fahrplan gefunden." Hier muss noch ein Bild für Erstellt werden!
    	}else if (count==1){
    		return "/resources/images/fahrplaene/KeinFahrplan.png";
    	}else{
    		//Hier Nummer und Bild!
    		return "/resources/images/fahrplaene/DummyFahrplan.png";
    	}
    	*/
    }


}
