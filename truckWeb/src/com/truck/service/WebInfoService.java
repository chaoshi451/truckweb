package com.truck.service;

import org.nutz.dao.Dao;
import org.nutz.service.IdEntityService;

import com.truck.entity.User;

/**
 * ��վ��Ϣ������
 * 
 * @author shiChao
 */
public class WebInfoService extends IdEntityService<User> {
	
	public WebInfoService() {
		super();
	}

	public WebInfoService(Dao dao) {
		super(dao);
	}

	
}
