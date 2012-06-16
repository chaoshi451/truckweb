package com.truck.mvc;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.entity.User;
import com.truck.service.WebInfoService;

/**
 *  网站信息模块类
 * @author shiChao
 *
 */
@InjectName("webInfoModule")
@At("/webInfo")
@Fail("json")
public class WebInfoModule {

	private WebInfoService webInfoService;
	
	/**
	 * 保存产品属性信息
	 * @param attributeValue
	 */
	@At
	@Ok("json")
	public String userLogin(@Param("userCode")String userCode, @Param("userPwd") String userPwd, HttpServletRequest req) {
		User user = webInfoService.fetch(Cnd.where("code", "=", userCode).and("password", "=", userPwd));
		if (user != null) {
			req.getSession().setAttribute("user", user);
			return "ok";
		}
		return "fail";
	}

	public WebInfoService getAttributeService() {
		return webInfoService;
	}

	public void setWebInfoService(WebInfoService webInfoService) {
		this.webInfoService = webInfoService;
	}
	
}
