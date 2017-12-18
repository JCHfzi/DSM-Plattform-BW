// TEst später Lösch
package de.dsm.services.flex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import de.dsm.data.dto.FlexibilityDTO;

@ManagedBean(name = "dummyFlexibilityProvider")
@ApplicationScoped
public class DummyFlexibilityService {
	
    private final static String[] brands;
    
    static {
         
        brands = new String[10];
        brands[0] = "Flughhafen Stuttgart";
        brands[1] = "Porsche";
        brands[2] = "Daimler";
        brands[3] = "Bosch";
        brands[4] = "Male";
        brands[5] = "Thales";
        brands[6] = "Schindler";
        brands[7] = "Deutsche Bahn";
        brands[8] = "Grober";
        brands[9] = "Mövenpick Hotel";
    }
     
    public List<FlexibilityDTO> createFlexibilites(int size) {
        List<FlexibilityDTO> list = new ArrayList<FlexibilityDTO>();
        list.add(new FlexibilityDTO(getRandomId(), brands[0], getRandomPrice(), getRandomSoldState(),"fsg-logo-de.svg"));
        list.add(new FlexibilityDTO(getRandomId(), brands[9], getRandomPrice(), getRandomSoldState(),"mvn2.png"));
        for(int i = 2 ; i < size ; i++) {
            list.add(new FlexibilityDTO(getRandomId(), getRandomBrand(), getRandomPrice(), getRandomSoldState(),"ataturkparki.png"));
        }
         
        return list;
    }
     
    private String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
     
    private String getRandomBrand() 
    {
    	int rand=(int) (Math.random() * 10);
    	if (rand<=1) rand=rand+2;
        return brands[rand];
    }
     
    public int getRandomPrice() {
        return (int) (Math.random() * 100000);
    }
     
    public boolean getRandomSoldState() {
        return (Math.random() > 0.5) ? true: false;
    }
 
    public List<String> getBrands() {
        return Arrays.asList(brands);
    }


}
