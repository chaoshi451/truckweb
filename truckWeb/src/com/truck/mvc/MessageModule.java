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
 * ����ģ����
 * @author shiChao
 *
 */
@InjectName("messageModule")
@At("/message")
@Fail("json")
public class MessageModule {

	private MessageService messageService;
	
	/**
	 * ��ѯ���е���Ʒ
	 * @return ��Ʒ�б�
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
	 * �����µĲ�Ʒ��Ϣ
	 * @param attributeValues
	 * @param request
	 * @return ������Ʒ��ID
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
		System.out.println("�����������ݳɹ���");
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
