package com.truck.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.truck.entity.AttributeType;

public class ProductDTO {
	
	private int id;

	private String name;

	private Date lastUpdateDate;

	private String author;

	private String content;

	private int porder;
	
	private String picPath;
	
	private List<String> elsePics;
	
	/** 产品类型 */
	private List<AttributeType> proTypes;
	
	/** 产品类型属性 */
	private Map<String, List<String>> attributeValues;
	
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

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public List<String> getElsePics() {
		return elsePics;
	}

	public void setElsePics(List<String> elsePics) {
		this.elsePics = elsePics;
	}

	public List<AttributeType> getProTypes() {
		return proTypes;
	}

	public void setProTypes(List<AttributeType> proTypes) {
		this.proTypes = proTypes;
	}

	public Map<String, List<String>> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Map<String, List<String>> attributeValues) {
		this.attributeValues = attributeValues;
	}

}
