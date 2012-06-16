package com.truck.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * ≤˙∆∑ Ù–‘
 * @author shiChao
 *
 */
@Table("t_attribute")
public class Attribute implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int id;

	@Column
	private String name;
	
	@Column
	private boolean isCompare;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompare() {
		return isCompare;
	}

	public void setCompare(boolean isCompare) {
		this.isCompare = isCompare;
	}

}
