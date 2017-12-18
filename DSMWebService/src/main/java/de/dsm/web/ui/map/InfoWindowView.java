package de.dsm.web.ui.map;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
  
//@ManagedBean(name="infoWindowView")
//@RequestScoped
@Component
@Scope("session")

public class InfoWindowView implements Serializable {
      
    /**
	 * 
	 */
	private static final long serialVersionUID = 6185582118614742279L;

	private MapModel advancedModel;
  
    private Marker marker;
  
    
    
    public MapModel getAdvancedModel() {
    	Logger.getLogger("InfoWindowView").info("I'm at getAdvancedModel"+advancedModel);
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
    	Logger.getLogger("InfoWindowView").info("I'm at onMarkerSelect");
        marker = (Marker) event.getOverlay();
    }
      
    public Marker getMarker() {
    	Logger.getLogger("InfoWindowView").info("I'm at getMarker");
        return marker;
    }


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}


	public void setMarker(Marker marker) {
		this.marker = marker;
		

	}
 

}