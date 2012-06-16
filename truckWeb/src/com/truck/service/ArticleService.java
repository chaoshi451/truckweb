package com.truck.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.service.IdEntityService;

import com.truck.entity.Article;
import com.truck.util.PageModule;

/**
 * 新闻服务类
 * 
 * @author shiChao
 */
public class ArticleService extends IdEntityService<Article> {
	
	public ArticleService() {
		super();
	}

	public ArticleService(Dao dao) {
		super(dao);
	}
	
	public List<Article> getArticleByPage(PageModule<Article> pageModule){
		Pager pager = this.dao().createPager(pageModule.getPage(), pageModule.getPageSize());
		return this.query(Cnd.orderBy().desc("id"), pager);
	}
}
