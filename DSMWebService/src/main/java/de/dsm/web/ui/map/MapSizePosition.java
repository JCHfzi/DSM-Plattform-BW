package de.dsm.web.ui.map;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.gmap.GMap;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.LatLng;

@RequestScoped
@ManagedBean(name="MapSizePosition")

public class MapSizePosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2325866521439115292L;
	


	private String focus;
	private Integer zoom;

	@PostConstruct
	public void init(){
	     
		zoom = 10;
		focus="48.6911209, 9.1871881";
	    
	}
	
	
	public void onStateChange(StateChangeEvent event) { 
		
		   zoom = event.getZoomLevel();
		   focus=event.getCenter().toString();
		}
	

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}

	public Integer getZoom() {
		return zoom;
	}

	public void setZoom(Integer zoom) {
		this.zoom = zoom;
	}
	

	

}

