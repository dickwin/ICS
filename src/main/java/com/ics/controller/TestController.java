package com.ics.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ics.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	private static Logger log = LogManager.getLogger(TestController.class.getName());

	@Autowired
	private TestService test;
    
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	@ResponseBody
    public Object getTest() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			List rs = test.getTest(paramMap);
			data.put("data",rs);
			data.put("len",rs.size());
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return data;
    }
	
	@RequestMapping(value = "/addTest", method = RequestMethod.GET)
	@ResponseBody
    public Object addTest() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = test.addTest(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/delTest", method = RequestMethod.GET)
	@ResponseBody
    public Object delTest() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = test.delTest(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/updateTest", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = test.updateTest(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
}