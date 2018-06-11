package com.ics.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
public class PorxyController {

	@RequestMapping(value = "/proxyMyIncomeInfo", method = RequestMethod.GET)
	@ResponseBody
	public String greeting(@RequestParam(value = "client_code_fromdate", required = false) String client_code_fromdate,
			@RequestParam(value = "client_code_enddate", required = false) String client_code_enddate,
			@RequestParam(value = "pageindex", required = false, defaultValue = "1") String pageindex,
			@RequestParam(value = "pagesize", required = false, defaultValue = "1000") String pagesize) {
		// hardcode param
		String url = "http://10.2.75.32:32023/api/datacloud/hbaseapi/1.0.1/batch/custIncome";
		//String url = "http://www.baidu.com";
		String secret = "8ae6861b-8ef4-4166-a1a4-3ee0ad819f9c";
		long timeStamp = System.currentTimeMillis();
		String password = DigestUtils.md5Hex(secret + timeStamp);
		HttpPost httpPost = new HttpPost(url);
		//param from cliet		
        List m = new ArrayList();
        m.add("eq,startRow,"+client_code_fromdate);
        m.add("eq,stopRow,"+client_code_enddate);
        JSONObject jsonObject = new JSONObject();  
        jsonObject.put("source", "robot");
        jsonObject.put("timeStamp", timeStamp);
        jsonObject.put("password", password);
        jsonObject.put("pageindex", pageindex);
        jsonObject.put("pagesize", pagesize);
        jsonObject.put("conditions", m);

        //System.out.println(jsonObject.toString());
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);

		post.addHeader("content-type", "application/json;charset=utf-8");
		post.addHeader("accept", "application/json");
		// json字符串以实体的实行放到post中
		post.setEntity(new StringEntity(jsonObject.toString(), Charset.forName("utf-8")));
		HttpResponse response = null;
		String resultstr = "";
		try {
			response = client.execute(post);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
			//"请求返回错误请联系管理员
			resultstr ="1";
		}else{
			try {
				resultstr = EntityUtils.toString(response.getEntity());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultstr;
	}

}