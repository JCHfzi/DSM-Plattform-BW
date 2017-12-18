package de.dsm.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "matchingFlexibilities")
@RequestScoped
public class MatchingFlexibilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	
	private List<FlexiblePower> entries;
	
	public MatchingFlexibilities(){
		super();
	}

	public List<FlexiblePower> getEntries() {
		return entries;
	}

	public void setEntries(List<FlexiblePower> entries) {
		this.entries = entries;
	}

	public MatchingFlexibilities(List<FlexiblePower> entries) {
		super();
		this.entries = entries;
	}
}
