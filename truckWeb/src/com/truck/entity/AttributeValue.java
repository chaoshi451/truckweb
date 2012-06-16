package com.truck.entity;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

/**
 * 产品属性值
 * @author shiChao
 *
 */
@Table("t_attributevalue")
public class AttributeValue implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	@Id
	private int id;

	@Many(target = Product.class, field = "productId")
	private Product product;
	
	@Many(target = Attribute.class, field = "attributeId")
	private Attribute attribute;
	
	@Many(target = Product.class, field = "attributeTypeId")
	private AttributeType attributeType;

	@Column
	private String text;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(AttributeType attributeType) {
		this.attributeType = attributeType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
