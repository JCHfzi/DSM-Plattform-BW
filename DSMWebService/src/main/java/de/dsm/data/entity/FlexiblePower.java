// Flexibilität, die auf der Website als Suchergebnis angezeigt wird

package de.dsm.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)

@Table(name = "FLEXIBLE_POWER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "SID")})


public class FlexiblePower implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SID", unique = true, nullable = false)
	private Long sid; //
	
	
	@Column(name = "CAT_NO", nullable = false)
	private Long categoryNo; //

	@Column(name = "CATEGORY", unique = false, nullable = false)
	private String category;

	@Column(name = "CAT_SHORT", unique = false, nullable = false)
	private String categoryCode;
	
	@ManyToOne
	@JoinColumn(name="FLEXID")
	private Flexibility flexibility;


	public FlexiblePower() {
		
	}
	
	public Long getSid() {
		return sid;
	}


	public void setSid(Long sid) {
		this.sid = sid;
	}


	public Long getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Flexibility getFlexibility() {
		return flexibility;
	}


	public void setFlexibility(Flexibility flexibility) {
		this.flexibility = flexibility;
	}
	
}
