package com.truck.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.service.IdEntityService;

import com.truck.entity.Message;
import com.truck.util.PageModule;

/**
 * ¡Ù—‘∑˛ŒÒ¿‡
 * 
 * @author shiChao
 */
public class MessageService extends IdEntityService<Message> {
	
	public MessageService() {
		super();
	}

	public MessageService(Dao dao) {
		super(dao);
	}
	
	public List<Message> getMessageByPage(PageModule<Message> pageModule){
		Pager pager = this.dao().createPager(pageModule.getPage(), pageModule.getPageSize());
		return this.query(Cnd.orderBy().desc("id"), pager);
	}
}
