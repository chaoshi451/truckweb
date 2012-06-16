package com.truck.mvc;

import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.entity.Links;
import com.truck.service.LinksService;
import com.truck.util.PageModule;

/**
 * 留言模块类
 * @author shiChao
 *
 */
@InjectName("linksModule")
@At("/links")
@Fail("json")
public class LinksModule {

	private LinksService linksService;
	
	/**
	 * 查询所有的商品
	 * @return 商品列表
	 */
	@At
	@Ok("json")
	public PageModule<Links> allByPage(@Param("page") int page, @Param("rows") int pageSize, @Param("flag") int flag) {
		PageModule<Links> pageModule = new PageModule<Links>(page, linksService.count(), pageSize);
		List<Links> messageList = linksService.getLinksByPage(pageModule, flag);
		pageModule.setRows(messageList);
		return pageModule;
	}
	
	/**
	 * 保存新的产品信息
	 * @param attributeValues
	 * @param request
	 * @return 新增产品的ID
	 */
	@At
	@Ok("json")
	@Fail("json")
	public int saveNewLinks(@Param("::links.") Links links) {
		if (links.getId() != 0) {
			linksService.dao().update(links);
		} else {
			linksService.dao().insert(links);
		}
		return links.getId();
	}
	
	@SuppressWarnings("unchecked")
	@At
	@Ok("json")
	@Fail("json")
	public String saveAllLinks(@Param("links") Links[] links, @Param("flag") int flag){
		for (Links link : links) {
			link.setFlag(flag);
			this.saveNewLinks(link);
		}
		return "ok";
	}
	
	@At
	@Ok("json")
	public boolean delLinks(@Param("linksId") int linksId) {
		return linksService.delete(linksId) > 0;
	}

	public LinksService getLinksService() {
		return linksService;
	}

	public void setLinksService(LinksService linksService) {
		this.linksService = linksService;
	}

}
