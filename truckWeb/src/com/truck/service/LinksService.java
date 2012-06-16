package com.truck.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.service.IdEntityService;

import com.truck.entity.Links;
import com.truck.util.PageModule;

/**
 * 友情链接服务类
 * 
 * @author shiChao
 */
public class LinksService extends IdEntityService<Links> {
	
	public LinksService() {
		super();
	}

	public LinksService(Dao dao) {
		super(dao);
	}
	
	public List<Links> getLinksByPage(PageModule<Links> pageModule, int flag){
		Pager pager = this.dao().createPager(pageModule.getPage(), pageModule.getPageSize());
		return this.query(Cnd.where("flag", "=", flag).asc("id"), pager);
	}
}
