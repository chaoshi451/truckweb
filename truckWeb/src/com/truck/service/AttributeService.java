package com.truck.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.service.IdEntityService;

import com.truck.entity.Attribute;
import com.truck.entity.AttributeType;

/**
 * 产品属性服务类
 * 
 * @author shiChao
 */
public class AttributeService extends IdEntityService<Attribute> {
	
	public AttributeService() {
		super();
	}

	public AttributeService(Dao dao) {
		super(dao);
	}

	public List<Map<String, Object>> getAllAttributeTypes() {
		List<AttributeType> list = this.dao().query(AttributeType.class, null, null);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (AttributeType attributeType : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", attributeType.getId());
			map.put("text", attributeType.getText());
			returnList.add(map);
		}
		return returnList;
	}
	
	public List<Map<String, Object>> getAllAttributes() {
		List<Attribute> list = this.dao().query(Attribute.class, null, null);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (Attribute attribute : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", attribute.getId());
			map.put("text", attribute.getName());
			map.put("isCompare", attribute.isCompare());
			returnList.add(map);
		}
		return returnList;
	}
}
