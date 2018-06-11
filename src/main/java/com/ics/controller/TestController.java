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
import com.ics.vo.Result;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	private static Logger log = LogManager.getLogger(TestController.class.getName());

	@Autowired
	private TestService test;
    
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	@ResponseBody
    public Object getTest() {
		Result result = new Result(1, null);
		try {
			List rs = test.getTest(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return result;
    }
	
	@RequestMapping(value = "/addTest", method = RequestMethod.GET)
	@ResponseBody
    public Object addTest() {
		Result result = new Result(1, null);
		try {
			int rs = test.addTest(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/delTest", method = RequestMethod.GET)
	@ResponseBody
    public Object delTest() {
		Result result = new Result(1, null);
		try {
			int rs = test.delTest(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/updateTest", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Result result = new Result(1, null);
		try {
			int rs = test.updateTest(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
}