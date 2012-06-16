package com.truck.entity;

import java.io.Serializable;
import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 产品信息
 * @author shiChao
 *
 */
@Table("t_productinfo")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int id;

	@Column
	private String name;

	@Column
	private Date lastUpdateDate;

	@Column
	private String author;

	@Column
	private String content;

	@Column
	private int porder;
	
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

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPorder() {
		return porder;
	}

	public void setPorder(int porder) {
		this.porder = porder;
	}

}
