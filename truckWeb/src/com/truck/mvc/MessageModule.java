package com.truck.mvc;

import java.util.Date;
import java.util.List;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.entity.Message;
import com.truck.service.MessageService;
import com.truck.util.PageModule;

/**
 * 留言模块类
 * @author shiChao
 *
 */
@InjectName("messageModule")
@At("/message")
@Fail("json")
public class MessageModule {

	private MessageService messageService;
	
	/**
	 * 查询所有的商品
	 * @return 商品列表
	 */
	@At
	@Ok("json")
	public PageModule<Message> allByPage(@Param("page") int page, @Param("rows") int pageSize) {
		PageModule<Message> pageModule = new PageModule<Message>(page, messageService.count(), pageSize);
		List<Message> messageList = messageService.getMessageByPage(pageModule);
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
	public int saveNewMessage(@Param("::message.") Message message) {
		message.setCreateDate(new Date());
		if (message.getId() != 0) {
			messageService.dao().update(message);
		} else {
			messageService.dao().insert(message);
		}
		System.out.println("保存留言内容成功！");
		return message.getId();
	}
	
	@At
	@Ok("json")
	public Message getMessageById(@Param("messageId") int messageId) {
		return messageService.fetch(messageId);
	}
	
	@At
	@Ok("json")
	public boolean delMessage(@Param("messageId") int messageId) {
		return messageService.delete(messageId) > 0;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
}
