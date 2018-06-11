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

import com.ics.service.AbcService;
import com.ics.vo.Result;

@Controller
@RequestMapping("/abc")
public class AbcController extends BaseController{
	private static Logger log = LogManager.getLogger(AbcController.class.getName());

	@Autowired
	private AbcService abc;
    
	@RequestMapping(value = "/getAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object getAbc() {
		Result result = new Result(1, null);
		try {
			List rs = abc.getAbc(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return result;
    }
	
	@RequestMapping(value = "/addAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object addAbc() {
		Result result = new Result(1, null);
		try {
			int rs = abc.addAbc(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/delAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object delAbc() {
		Result result = new Result(1, null);
		try {
			int rs = abc.delAbc(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/updateAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Result result = new Result(1, null);
		try {
			int rs = abc.updateAbc(paramMap);
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