package com.truck.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 产品类型
 * @author shiChao
 *
 */
@Table("t_attributetype")
public class AttributeType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int id;

	@Column
	private String text;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
