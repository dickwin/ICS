package com.ics.controller;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

@SuppressWarnings("rawtypes")
public class BaseController {

	//private static Logger logger = LogManager.getLogger(BaseController.class.getName());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected HashMap<String, String> paramMap;
	String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.response.setCharacterEncoding("utf-8");
		this.response.setContentType("text/html; charset=utf-8");
		this.session = request.getSession();
		this.getParamMap();
	}

	/**
	 * 获得paramMap
	 */
	private void getParamMap() {
		Enumeration paramNames = request.getParameterNames();
		paramMap = new HashMap<String, String>();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();

			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					paramMap.put(paramName, paramValue);
				}
			}
		}
	}

}
