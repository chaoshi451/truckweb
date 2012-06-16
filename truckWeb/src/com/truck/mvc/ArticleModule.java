package com.truck.mvc;

import java.util.Date;
import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.entity.Article;
import com.truck.service.ArticleService;
import com.truck.util.PageModule;

/**
 * ����ģ����
 * @author shiChao
 *
 */
@InjectName("articleModule")
@At("/article")
@Fail("json")
public class ArticleModule {

	private ArticleService articleService;
	
	/**
	 * ��ѯ���е���Ʒ
	 * @return ��Ʒ�б�
	 */
	@At
	@Ok("json")
	public PageModule<Article> allByPage(@Param("page") int page, @Param("rows") int pageSize) {
		PageModule<Article> pageModule = new PageModule<Article>(page, articleService.count(), pageSize);
		List<Article> articleList = articleService.getArticleByPage(pageModule);
		pageModule.setRows(articleList);
		return pageModule;
	}
	
	/**
	 * �����µĲ�Ʒ��Ϣ
	 * @param attributeValues
	 * @param request
	 * @return ������Ʒ��ID
	 */
	@At
	@Ok("json")
	@Fail("json")
	public int saveNewArticle(@Param("::article.") Article article) {
		article.setLastUpdateDate(new Date());
		if (article.getId() != 0) {
			articleService.dao().update(article);
		} else {
			articleService.dao().insert(article);
		}
		return article.getId();
	}
	
	@At
	@Ok("json")
	public Article getArticleById(@Param("articleId") int articleId) {
		return articleService.fetch(articleId);
	}
	
	@At
	@Ok("json")
	public boolean delArticle(@Param("articleId") int articleId) {
		return articleService.delete(articleId) > 0;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
}
