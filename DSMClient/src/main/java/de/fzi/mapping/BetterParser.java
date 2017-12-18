// Verarbeitet Daten des CSV des Flughafens und schreibt diese in eine Liste mit Datenpunkten

package de.fzi.mapping;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;

import org.supercsv.io.ICsvBeanReader;

import org.supercsv.prefs.CsvPreference;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.PrintWriter;

import de.fzi.domain.DatenPunkt;

public class BetterParser {


    // name mappings
 @SuppressWarnings("unused")
private static final String[] NORMAL_HEADER =    new String[] { "Datum", "Uhrzeit", "KomischerBuchstabe", "ID", "Beschreibung","Wert","Einheit", null };


 public static void main(String[] args) throws Exception {
	 
	 List<DatenPunkt> Liste = null;
     File downloadFile1 = new File("/home/dsm/sco_OS7_PDS2_130617_125526");
     
	 Liste = BetterParser.readWithCsvBeanReader(downloadFile1);
	System.out.println(Liste);
	// System.out.println(Liste.get(0).getDatum());
 }
 

 
 
    



    	public static List<DatenPunkt> readWithCsvBeanReader(File file) throws Exception {
    		
    		List<DatenPunkt> ret = new ArrayList<DatenPunkt>();
    		
    		// Entferne Leere Zeilen
    		
            Scanner fileBlank;
            PrintWriter writer;

            try {

            	fileBlank = new Scanner(new File(file.getAbsolutePath()));
                writer = new PrintWriter("tmp2.tmp");

                
                
                while (fileBlank.hasNext()) {
                    String line = fileBlank.nextLine();
                    if (!line.isEmpty()) {
                        writer.write(line);
                        writer.write("\n");
                    }
                }

                fileBlank.close();
                writer.close();

                
            } catch (FileNotFoundException ex) {
                
            }
            File file2 = new File("tmp2.tmp");
            
    
    		
        // using bean reader and list reader together (to read the same file)
        
        ICsvBeanReader beanReader = null;
        
        try {
                beanReader =new CsvBeanReader(new FileReader(file2), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

                
        
                // the header elements are used to map the values to the bean (names must match)
                final String[] header = new String[]{"Datum","Uhrzeit","KomischerBuchstabe","ID","Beschreibung","Wert","Einheit",null, null};
                final CellProcessor[] processors = getProcessors();
                
                DatenPunkt customer;
                while( (customer = beanReader.read(DatenPunkt.class, header, processors)) != null ) {
                        //System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s, customers=%s", beanReader.getLineNumber(),beanReader.getRowNumber(), customer,customer.getBeschreibung()));
                         ret.add(customer);
                
                }
                
        }
        finally {
                if( beanReader != null ) {
                        beanReader.close();
                        return ret;    
                }
        }
        
    		/*
            ICsvListReader listReader = null;
            try {
                    listReader = new CsvListReader(new FileReader("tmp2.tmp"), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
                    
                    listReader.getHeader(true); // skip the header (can't be used with CsvListReader)
                    final CellProcessor[] processors = getProcessors();
                    
                    List<Object> customerList;
                    while( (customerList = listReader.read(processors)) != null ) {
                            System.out.println(String.format("lineNo=%s, rowNo=%s, customerList=%s", listReader.getLineNumber(),
                                    listReader.getRowNumber(), customerList));
                    }
                    
            }
            finally {
                    if( listReader != null ) {
                            listReader.close();
                    }
            }	
    		*/
		return ret;
}
  
    		
    		
    		
    		

    
private static CellProcessor[] getProcessors() {
        
        
        final CellProcessor[] processors = new CellProcessor[] { 
                new ParseDate("dd.MM.yyyy"), // Date 
                new NotNull(), // Time
                new NotNull(), // Komischer Buchstabe
                new NotNull(), // ID
                new NotNull(), // Beschreibung
                new NotNull(), // Wert
                new Optional(), // Einheit
                new Optional(), // Rubbish
                new Optional() // Rubbish

        };
        
        return processors;
}


}