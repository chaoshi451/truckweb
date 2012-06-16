package com.truck.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * �������Ĺ�����
 * @author shichao
 */
public class SetCharacterEncodingFilter implements Filter {
	/**
	 * 
	 * �� HttpServletRequestWrapper ��������, ��Ӱ��ԭ���Ĺ��ܲ����ṩ���е� HttpServletRequest
	 * �ӿ��еĹ���. ������ͳһ�Ķ� Tomcat Ĭ�������µ�����������н����ֻ��Ҫ���µ� Request �����滻ҳ���е� request
	 * ���󼴿�.
	 */
	protected String encoding = null;
	protected FilterConfig filterConfig = null;

	// protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	class Request extends HttpServletRequestWrapper {

		public Request(HttpServletRequest request) {
			super(request);
		}

		/**
		 * ת���ɱ���ȡ�����ݵ�����. �� ISO �ַ�ת�� utf-8(��gbk).
		 */
		public String toChi(String input) {
			try {

				byte[] bytes = input.getBytes("ISO-8859-1");
				return new String(bytes, encoding);
			} catch (Exception ex) {
			}
			return null;
		}

		/**
		 * Return the HttpServletRequest holded by this object.
		 */
		private HttpServletRequest getHttpServletRequest() {
			return (HttpServletRequest) super.getRequest();
		}

		/**
		 * ��ȡ���� -- ��������������.
		 */
		public String getParameter(String name) {
			return toChi(getHttpServletRequest().getParameter(name));
		}

		/**
		 * ��ȡ�����б� - ��������������.
		 */
		public String[] getParameterValues(String name) {
			String values[] = getHttpServletRequest().getParameterValues(name);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					values[i] = toChi(values[i]);
				}
			}
			return values;
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpreq = (HttpServletRequest) request;
		if (httpreq.getMethod().equals("POST")) {
			request.setCharacterEncoding(encoding);
		} else {
			request = new Request(httpreq);
		}

		chain.doFilter(request, response);

	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");

	}

}