package com.truck.mvc;

import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Localization;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.JsonIocProvider;


/**
 * ����Ϊ����Ӧ�õ�Ĭ��ģ���ࡣ��������ϣ�����ԣ�
 * <ul>
 * <li>ͨ�� '@Modules' ע����������Ӧ������Щģ��
 * <li>ͨ�� '@IocBy' ע����������Ӧ�ã�Ӧ���ú��ַ�ʽ���з�תע�롣���û������������Ӧ�ý���֧�� Ioc
 * <li>ͨ�� '@Localization' ע����������Ӧ�õı��صػ��ַ�����Ŀ¼
 * <li>ͨ�� '@SetupBy' ע������Ӧ�������Ĺر�ʱ��Ӧ�ý��еĴ�����ͨ�� Setup �ӿڣ�
 * <li>ͨ�� '@Ok' ע����������Ӧ��Ĭ�ϵĳɹ���ͼ
 * <li>ͨ�� '@Fail' ע����������Ӧ��Ĭ�ϵ�ʧ����ͼ
 * </ul>
 * 
 * @author shiChao
 * 
 */
@Modules( { ProductModule.class, AttributeModule.class, FileModule.class,
		WebInfoModule.class, ArticleModule.class, MessageModule.class,
		LinksModule.class })
@IocBy(type = JsonIocProvider.class, args = { "dao.js", "productIoc.js",
		"attributeIoc.js", "articleIoc.js", "messageIoc.js", "linksIoc.js" })
// @SetupBy(HelloMvcSetup.class)
@Localization("msg")
@Fail("json")
public class MainModule {

}
