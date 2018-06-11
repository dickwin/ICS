package com.ics.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/client")
public class PorxyTestController {

	@RequestMapping(value = "/proxyTestMyIncomeInfo", method = RequestMethod.GET)
	@ResponseBody
	public String greeting(@RequestParam(value = "client_code_fromdate", required = false) String client_code_fromdate,
			@RequestParam(value = "client_code_enddate", required = false) String client_code_enddate,
			@RequestParam(value = "pageindex", required = false, defaultValue = "1") String pageindex,
			@RequestParam(value = "pagesize", required = false, defaultValue = "1000") String pagesize) {
		// hardcode param
		String url = "https://robotuat.gf.com.cn/api/robot/accdiag/1.0.0/client/proxyMyIncomeInfo?client_code_fromdate="+client_code_fromdate+"&client_code_enddate="+client_code_enddate;
	    //httpClient
	    HttpClient httpClient = new DefaultHttpClient();

	    // get method
	    HttpGet httpGet = new HttpGet(url);
	    
	    // set header
	    //String Au="Bearer "+access_token;
	    //httpGet.setHeader("Authorization",Au);  
	  
	    //response
	    HttpResponse response = null;  
	    try{
	        response = httpClient.execute(httpGet);
	    }catch (Exception e) {} 

	    //get response into String
	    String temp="";
	    try{
	        HttpEntity entity = response.getEntity();
	        temp=EntityUtils.toString(entity,"UTF-8");
	    }catch (Exception e) {} 
	    
	    return temp;
	}

}