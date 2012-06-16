package com.truck.service;

import org.nutz.dao.Dao;
import org.nutz.service.IdEntityService;

import com.truck.entity.User;

/**
 * 网站信息服务类
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
