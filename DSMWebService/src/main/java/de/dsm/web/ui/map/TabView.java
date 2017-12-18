package de.dsm.web.ui.map;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name="TabView")

public class TabView implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6349934516641123891L;
	
	private Integer activeTab;

	@PostConstruct
    public void init() {
		activeTab=0;
	}
	
	
	public Integer getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(Integer activeTab) {
		this.activeTab = activeTab;
	}
	public void setActiveTabKarte() {
		this.activeTab = 0;
	}
	public void setActiveTabListe() {
		this.activeTab = 1;
	}
	public void setActiveTabFahrplan() {
		this.activeTab = 2;
	}
	
}