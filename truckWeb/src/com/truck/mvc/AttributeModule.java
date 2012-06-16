package com.truck.mvc;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.truck.entity.AttributeValue;
import com.truck.service.AttributeService;

/**
 * ��Ʒ����ģ����
 * @author shiChao
 *
 */
@InjectName("attributeModule")
@At("/attribute")
@Fail("json")
public class AttributeModule {

	private AttributeService attributeService;
	
	/**
	 * ��ѯ���еĲ�Ʒ����
	 * @return ��Ʒ����
	 */
	@At
	@Ok("json")
	public List<Map<String, Object>> allTypes() {
		return attributeService.getAllAttributeTypes();
	}
	
	/**
	 * ��ѯ���еĲ�Ʒ����
	 * @return ��Ʒ����
	 */
	@At
	@Ok("json")
	public List<Map<String, Object>> allAtts() {
		return attributeService.getAllAttributes();
	}
	
	/**
	 * �����Ʒ������Ϣ
	 * @param attributeValue
	 */
	@At
	@Ok("json")
	public String addAllAttr(@Param("proId")int proId, @Param("typeId") int typeId, 
				@Param("attId") int attId, @Param("text") String text) {
		try {
//			AttributeValue attributeValue = attributeService.dao().fetch(
//					AttributeValue.class,
//					Cnd.where("attributeTypeId", "=", typeId).and("attributeId",
//							"=", attId));
			AttributeValue attributeValue = null;
			if (attributeValue != null) {
				attributeValue.setText(text);
				attributeService.dao().update(attributeValue);
			} else {
				Sql sql = Sqls.create("insert into `t_attributevalue`(`productId`,`attributeId`,`attributeTypeId`,`text`) values (@proId,@attId,@typeId,@text)");
				sql.params().set("proId", proId);
				sql.params().set("attId", attId);
				sql.params().set("typeId", typeId);
				sql.params().set("text", text);
				attributeService.dao().execute(sql);
			}
			return "ok";
		} catch (RuntimeException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public AttributeService getAttributeService() {
		return attributeService;
	}

	public void setAttributeService(AttributeService attributeService) {
		this.attributeService = attributeService;
	}
	
}
