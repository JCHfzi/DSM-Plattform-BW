package de.fzi.main;


import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import org.apache.commons.io.FileUtils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import de.fzi.domain.Flexibility;
import de.fzi.domain.FlexibilityImportObject;
import de.fzi.domain.FlexibilityPowerValues;
import de.fzi.domain.DatenPunkt;
import de.fzi.domain.FZICompany;
import de.fzi.mapping.BetterParser;


import de.fzi.domain.FlexAnlage;
/**
 
 * A program demonstrates how to upload files from local computer to a remote
 * FTP server using Apache Commons Net API.
 * @author www.codejava.net
 */
public class FHSClientMain2 {
	
	
		 List<FlexAnlage> anlage = new ArrayList<FlexAnlage>();
	 
	 public static void main(String[] args) {
		
		 
		 //Definiere Anlagen die für die jeweilige Beispielfirma am FLughafen revant sind
		   	List<FlexAnlage> anlageSolar = new ArrayList<FlexAnlage>(0);

		   	anlageSolar.add(new FlexAnlage("07/TIS__/FM____/348PM/STROM_____/MW/348PM_Photovoltaik_P20_P21",0.0,1500.0, "PV", 4.00,"KW"));
		   	anlageSolar.add(new FlexAnlage("07/TIS__/FM____/927KB/STROM_____/MW/927KB_Photovoltaik_KSB",0.0,500.0, "PV", 4.00,"KW"));
		   	anlageSolar.add(new FlexAnlage("07/TIS__/FM____/Geb__/Energiecoc/MW/Photovoltaik",0.0,3.0, "PV", 4.00,"MW"));
		   	anlageSolar.add(new FlexAnlage("07/TIS__/FM____/Geb__/STROM_____/MW/Photovoltaik_Summe",0.0,2500.0, "PV", 4.00,"KW"));

		   	
		   	List<FlexAnlage> anlageKM = new ArrayList<FlexAnlage>(0);
		   	anlageKM.add(new FlexAnlage("13/P02__/FM110_/119T1/K1.2______/MW/KM2_Leistung",0.0 ,400.0, "VariablerVerbraucher", 0.25,"KW"));
		   	anlageKM.add(new FlexAnlage("13/P02__/FM110_/119T1/K1.1______/MW/KM1_Leistung",0.0,400.0, "VariablerVerbraucher", 0.25,"KW"));

		   	List<FlexAnlage> anlageNEA = new ArrayList<FlexAnlage>(0);
		   	anlageNEA.add(new FlexAnlage("07/TIS__/FM____/Geb__/STROM_____/MW/Notstrom_Summe",0.0,2400.0, "Erz", 4.00,"KW"));
		   	
		   	List<FlexAnlage> anlageEV = new ArrayList<FlexAnlage>(0);
		   	anlageEV.add(new FlexAnlage("13/S12__/FM510_/119T1/SCHLEP_STR/MW/LG101_SZ.119.4000_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageEV.add(new FlexAnlage("13/S12__/FM51l0_/910VK/STR_______/MW/UV_SZ.910.2000_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageEV.add(new FlexAnlage("13/S12__/FM510_/910VK/BUS_STR___/MW/LG1_SZ.910.2010_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageEV.add(new FlexAnlage("13/S12__/FM510_/910VK/BUS_STR___/MW/HEIZ1_SZ.910.2100_P",0.0,20.0, "AbLa", 0.25,"KW"));


		   	
		 	List<FlexAnlage> anlageAlle = new ArrayList<FlexAnlage>(0);
		 	anlageAlle.add(new FlexAnlage("07/TIS__/FM____/348PM/STROM_____/MW/348PM_Photovoltaik_P20_P21",0.0,1500.0, "PV", 4.00,"KW"));
		 	anlageAlle.add(new FlexAnlage("07/TIS__/FM____/927KB/STROM_____/MW/927KB_Photovoltaik_KSB",0.0,500.0, "PV", 4.00,"KW"));
		 	anlageAlle.add(new FlexAnlage("07/TIS__/FM____/Geb__/Energiecoc/MW/Photovoltaik",0.0,3.0, "PV", 4.00,"MW"));
		 	anlageAlle.add(new FlexAnlage("07/TIS__/FM____/Geb__/STROM_____/MW/Photovoltaik_Summe",0.0,2500.0, "PV", 4.00,"KW"));
		 	anlageAlle.add(new FlexAnlage("13/P02__/FM110_/119T1/K1.2______/MW/KM2_Leistung",0.0 ,400.0, "VariablerVerbraucher", 0.25,"KW"));
		   	anlageAlle.add(new FlexAnlage("13/P02__/FM110_/119T1/K1.1______/MW/KM1_Leistung",0.0,400.0, "VariablerVerbraucher", 0.25,"KW"));
		   	anlageAlle.add(new FlexAnlage("07/TIS__/FM____/Geb__/STROM_____/MW/Notstrom_Summe",0.0,2400.0, "Erz", 4.00,"KW"));
		   	anlageAlle.add(new FlexAnlage("13/S12__/FM510_/119T1/SCHLEP_STR/MW/LG101_SZ.119.4000_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageAlle.add(new FlexAnlage("13/S12__/FM51l0_/910VK/STR_______/MW/UV_SZ.910.2000_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageAlle.add(new FlexAnlage("13/S12__/FM510_/910VK/BUS_STR___/MW/LG1_SZ.910.2010_P",0.0,20.0, "AbLa", 1.00,"KW"));
		   	anlageAlle.add(new FlexAnlage("13/S12__/FM510_/910VK/BUS_STR___/MW/HEIZ1_SZ.910.2100_P",0.0,20.0, "AbLa", 0.25,"KW"));

		   	
		   	// Lege Flughafen Beispielfirmen am FLughafen an
		   	FZICompany fhsAlle = new FZICompany("1001", "1002", "", "", "", "", "", "", "", "","", "", "", "", anlageAlle);
		   	
		   	//OK - kann nur nach unten
		   	FZICompany fhsSolar = new FZICompany("1010", "1011", "", "", "", "", "", "", "", "","", "", "", "", anlageSolar);		   	
		   	
		   	// ok - kann nach oben oder unten
		   	FZICompany fhsNEA = new FZICompany("1020", "1021","", "", "", "", "", "", "", "","", "", "", "",anlageNEA);	
		   
		   	// ok kann abgeschaltet werden, wenn läuft
		   	FZICompany fhsKM = new FZICompany("1018", "1019", "", "", "", "", "", "", "", "","", "", "", "", anlageKM);	
		   	
		   	// ok kann abgeschaltet werden, wenn läuft
		   	FZICompany fhsEV = new FZICompany("1022", "1023", "", "", "", "", "", "", "", "","", "", "", "", anlageEV);
// Timer
		   	
		   	 Runnable helloRunnable = new Runnable() {
		   		 
				    @Override
					public void run() {
					   	//System.out.println("Übertrage Aggregierte Flexibilität");
				    	
				    	
					    	start(fhsAlle);
						   	start(fhsSolar);
						   	start(fhsNEA);
						   	start(fhsKM);
						   	start(fhsEV);
				    
						   	ClearDataFromServer();
						   	
				    }
				};

				ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
				executor.scheduleAtFixedRate(helloRunnable, 0, 15, TimeUnit.MINUTES);
				//executor.scheduleAtFixedRate(helloRunnable, 0, 15, TimeUnit.SECONDS);

		   	/*Test*/
			//System.out.println("Beispiel 1");
		   	//start(fhsAlle);
//		   	System.out.println("Beispiel 2");
//		   	start(fhs2);
			
	 }
	 

		
    public static void start(FZICompany fhs) {
    	
    	//Verbinde und Lade Liste mit Datenpunkten vom Server
    	
    	
		List<DatenPunkt> Liste = GetDataFromServerNeu();
           	
    	
    	// Übersetze DatenPunkte in Flexibilitäten
    	List<Flexibility> FlexListe = new ArrayList<Flexibility>();
    	
    	if (Liste != null){
           	for (DatenPunkt temp : Liste) {
        			// System.out.println(temp.getID());
           		
           		
           		
           		if (temp.toFlexibility(fhs)!=null){
        			FlexListe.add(temp.toFlexibility(fhs));

           		}
        		
           	}
        	
           	// Berücksichtige nur Relevante Anlagen die zum Unternehmen gehörten
           	
           	List<Flexibility> FlexListeRelvant = reduceList(FlexListe, fhs.getAnlagen());
           	
           	// Fasse Flexibilitäten für jeden Zeitpunkt zusammen

           	List<Flexibility> FlexListeAggregiert = aggregateList(FlexListeRelvant, fhs);       	
           	FlexListeRelvant = FlexListeAggregiert;
           	// Mache FlexibilityImportObject daraus
           	
           	
      
           	
           	
           	try {
    			sendToRest(packObject(FlexListeRelvant));
    		} catch (IOException | UnirestException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    	

       	
       	
      
        
    }
    
    public static FlexibilityImportObject packObject(List<Flexibility> FlexListe){
    	List<FlexibilityPowerValues> Zeitpunkte = new ArrayList<FlexibilityPowerValues>(0);
    	
    	for (Flexibility tempZeitpunkt : FlexListe) {
    	Zeitpunkte.add(new FlexibilityPowerValues(tempZeitpunkt.getpMinus_4(),tempZeitpunkt.getpMinus_1(),tempZeitpunkt.getpMinus_1_4(),tempZeitpunkt.getpPlus_4(),tempZeitpunkt.getpPlus_1(),tempZeitpunkt.getpPlus_1_4(),tempZeitpunkt.getTS1(),tempZeitpunkt.getTS2()));
    	}
    	
    	FlexibilityImportObject flex= new FlexibilityImportObject(FlexListe.get(0).getUid(), FlexListe.get(0).getFid(), Zeitpunkte);
    
    	return flex;
    }
    
    public static List<Flexibility> aggregateList(List<Flexibility> FlexListe, FZICompany fhs){
    	
    	List<Flexibility> newlist = new ArrayList<Flexibility> ();
    	Flexibility tempFelx =FlexListe.get(0);

    	
    	//Gehe durch Liste, angefangen beim zweiten Eintrag
    	for (int i = 1; i < FlexListe.size(); i++) {
    		
    		//Wenn der aktuell Eintrag == dem vorhergehenden
    		if (FlexListe.get(i).getTS1().equals(FlexListe.get(i-1).getTS1())) {

    			
    			//Addiere die Flexpotentiale
    			tempFelx.setpMinus_1(tempFelx.getpMinus_1()+FlexListe.get(i).getpMinus_1());
    			tempFelx.setpMinus_4(tempFelx.getpMinus_4()+FlexListe.get(i).getpMinus_4());
    			tempFelx.setpMinus_1_4(tempFelx.getpMinus_1_4()+FlexListe.get(i).getpMinus_1_4());
    			tempFelx.setpPlus_1(tempFelx.getpPlus_1()+FlexListe.get(i).getpPlus_1());
    			tempFelx.setpPlus_1_4(tempFelx.getpPlus_1_4()+FlexListe.get(i).getpPlus_1_4());
    			tempFelx.setpPlus_4(tempFelx.getpPlus_4()+FlexListe.get(i).getpPlus_4());
    			
    			//Wenn es der letzte EIntrag ist. Schreibe Aggregat in Liste
    			if (i == FlexListe.size()-1) {
    		    	tempFelx.setFid(fhs.getfId());
    				newlist.add(tempFelx);
    			}
    		} else {
    			// Bei Wechsel des Zeitpunktes füge das bisherige Aggregat der Liste hinzu
    	    	tempFelx.setFid(fhs.getfId());
    			newlist.add(tempFelx);
    			
    			//Beginne eine neues Aggregat
    			tempFelx = FlexListe.get(i);
    			
    			
    			//Wenn es der letzte EIntrag ist. Schreibe Aggregat in Liste
    			if (i == FlexListe.size()-1) {
    		    	tempFelx.setFid(fhs.getfId());
    				newlist.add(tempFelx);
    			}
    			
    		}
		
    		
    	}
    	
    	
		return newlist;
	}
    
    // Entferne alle Flexibilitäten, die nicht von Anlagen aus der übergebenen Anlagenliste stammen
	public static List<Flexibility> reduceList(List<Flexibility> Flexibility, List<FlexAnlage> Anlagen){
    	
    	List<Flexibility> newlist = new ArrayList<Flexibility> (); 
    	
    	

    	for (int i = 0; i < Flexibility.size(); i++) {
    		  for (int j = 0; j < Anlagen.size(); j++) {
  
    		    if (Flexibility.get(i).getFid().equals(Anlagen.get(j).getFid())){

    		    	newlist.add(Flexibility.get(i));
    		    }
    		    
    		  }
    		}
    	
    	//Entferne Doppelte EInträge
       	HashSet<Flexibility> hashSet = new HashSet<Flexibility>(newlist);
       	newlist.clear();
       	newlist.addAll(hashSet);
    	
       	// Sortiere nach Datum
       	newlist.sort((Flexibility z1, Flexibility z2) -> {
       	   if (z1.getTS1().compareTo(z2.getTS1())<0)
       	     return -1;
       	   if (z1.getTS1().compareTo(z2.getTS1())>=0)
       	     return 1;
       	   return 0;
       	});
       	
       	
		return newlist;}
    
	
	public static void ClearDataFromServer(){
		
    	String server = "PLACEHOLDER";
        int port = 21;
        String user = "PLACEHOLDER";
        String pass = "PLACEHOLDER";

        FTPClient ftpClient = new FTPClient();

        
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            

            // Lade Liste aus CSV
            FTPFile[] ftpFiles;
			ftpFiles = ftpClient.listFiles();
			if (ftpFiles != null && ftpFiles.length > 0) {
	            		//loop thru files
			            for (FTPFile file : ftpFiles) {
			            	
			                if (!file.isFile()) {
			                    continue;
			                }
			                
					        //delete the file
			                ftpClient.deleteFile(file.getName());
			            }
			}
            
            
            //            
            

// 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
      
   
  
        
	}
    
    
    // Lade die cvs vom Server und gib eine Liste mit Datenpunkten zurück für mehre Dateien
    public static List<DatenPunkt> GetDataFromServerNeu() {
        
    	String server = "PLACEHOLDER";
        int port = 21;
        String user = "PLACEHOLDER";
        String pass = "PLACEHOLDER";
        List<DatenPunkt> Liste = null;
        FTPClient ftpClient = new FTPClient();

        
        try {
 
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            

            // Lade Liste aus CSV
            Liste =getListNeu(ftpClient, "sco_OS7");
            
            ftpClient.quit();
            //            
            

// 
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return Liste;
    }   
    

//Lese alle Daten vom Server in eine Liste und lösche dann die Daten

public static List<DatenPunkt> getListNeu(FTPClient ftpClient, String StartName) throws Exception {
	


	
	List<DatenPunkt> ListeVollStaendig = null;
	Integer Counter=0;
	
    // gehe durch alle Dateien
	
    //get list of filenames
    FTPFile[] ftpFiles = ftpClient.listFiles();

    
    if (ftpFiles != null && ftpFiles.length > 0) {
        //loop thru files
        for (FTPFile file : ftpFiles) {
        	
            if (!file.isFile()) {
                continue;
            }
            
            if (file.getName().substring(0, StartName.length()).equals(StartName)) {
            	//System.out.println("File is " + file.getName());
            	
        		
        		
                
                	InputStream iStream=ftpClient.retrieveFileStream(file.getName());
                	ftpClient.completePendingCommand();
                	
            	        
                File tempfile = File.createTempFile("tmp", ".tmp");
                FileUtils.copyInputStreamToFile(iStream, tempfile);
                
    
                
        		iStream.close();
        		//System.out.println(tempfile);
        		


        		List<DatenPunkt> Liste = null;
        		Liste =  BetterParser.readWithCsvBeanReader(tempfile);
        		
        		
        		if (Counter==0){
        			ListeVollStaendig=Liste;
        		}else{
        			ListeVollStaendig.addAll(Liste);
        		}
        		
        		Counter = Counter+1;
        		
                //delete the file
                //ftpClient.deleteFile(file.getName());
            }
            
            



        }
    }

    	if(ListeVollStaendig != null) {
	    	
    		System.out.println(ListeVollStaendig.size());
    		

    	}
		else{
	        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        System.out.println( sdf.format(cal.getTime()) );
			System.out.println("Keine Daten auf dem Server!");
		}	
    	
	return ListeVollStaendig;
}


public static void sendToRest(FlexibilityImportObject flex)
		throws IOException, UnirestException {


//// Alter Code
//	FileWriter fw = new FileWriter("C:\\Users\\Laustere\\DSMJava\\Properties\\OneFlex.txt");
//
//	Map<String, TreeMap<Datentypen, Double>> key = new TreeMap<String, TreeMap<Datentypen, Double>>();
//
//
//		TreeMap<Datentypen, Double> value = new TreeMap<>();
//		value.put(Datentypen.pPlus1, flex.getpPlus_1());
//		value.put(Datentypen.pPlus4, flex.getpPlus_1());
//		value.put(Datentypen.pPlus1_4, flex.getpPlus_1());
//		value.put(Datentypen.pMinus1, flex.getpPlus_1());
//		value.put(Datentypen.pMinus4, flex.getpPlus_1());
//		value.put(Datentypen.pMinus1_4, flex.getpPlus_1());
//		key.put(flex.getTS1(), value);
//
//
// String json = new ObjectMapper().writeValueAsString(key);
	
//	List<FlexibilityPowerValues> Zeitpunkte = new ArrayList<FlexibilityPowerValues>(0);
//	Zeitpunkte.add(new FlexibilityPowerValues(1.0,2.0,2.0,2.0,2.0,2.0,"2016-09-23T11:15:00.000Z","2016-09-23T11:30:00.000Z"));
//	Zeitpunkte.add(new FlexibilityPowerValues(1.0,2.0,2.0,2.0,2.0,2.0,"2016-09-23T11:30:00.000Z","2016-09-23T11:45:00.000Z"));
//   	
//	FlexibilityImportObject beispielFlex = new FlexibilityImportObject("Uid1","Fid1",Zeitpunkte);
//   	

	
//// Erzeuge JSON aus Flexibilität
	String json = new ObjectMapper().writeValueAsString(flex);
	System.out.println(json);
	
	//Alte Daten speichern
    //PrintWriter writer = new PrintWriter("/home/dsm/Development/workspace/DSMClient/src/test/resources/Data-From-20170614.txt", "UTF-8");
    //writer.println(json);
    //writer.close();
	
////Alter Code
//	json = "{ \"uid\": \"" + flex.getUid() + "\" , \"fid \": \""			+ flex.getFid() + "\" , \"Zeitreihen \": [" + json + "]} ";
//	
//	json = json.substring(0, (json.length()-1));
//	json.trim();
//	json.trim();
	
	

	//Test funktioniert Deserialisierung
	FlexibilityImportObject freshlyImported = new ObjectMapper().readValue(json, FlexibilityImportObject.class);
	System.out.println(freshlyImported.getFid());
	
	// Test funktioniert RestSchnittstelle
	//HttpResponse<String>response = Unirest.get("http://localhost:8280/DSM/rest/test").asString();
	//System.out.println(response.getBody());
	
	HttpResponse<String>response = Unirest.get("http://dsm.acosnms.de/DSM/rest/test").asString();
	System.out.println(response.getBody());
	
	// Poste JSON an Rest
	//HttpResponse<String> response2 = Unirest.post("http://localhost:8280/DSM/rest/receiveDataFromClient").body(json).asString();
	//System.out.println(response2.getBody() +" "+ response2.getStatusText());
	HttpResponse<String> response2 = Unirest.post("http://dsm.acosnms.de/DSM/rest/receiveDataFromClient").body(json).asString();
	System.out.println(response2.getBody() +" "+ response2.getStatusText());
	
//	fw.write(json);
//	fw.flush();
//	fw.close();

}
    
}