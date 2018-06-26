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

@Controller
@RequestMapping("/abc")
public class AbcController extends BaseController{
	private static Logger log = LogManager.getLogger(AbcController.class.getName());

	@Autowired
	private AbcService abc;
    
	@RequestMapping(value = "/getAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object getAbc() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			List rs = abc.getAbc(paramMap);
			data.put("data",rs);
			data.put("len",rs.size());
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return data;
    }
	
	@RequestMapping(value = "/addAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object addAbc() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = abc.addAbc(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/delAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object delAbc() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = abc.delAbc(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/updateAbc", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = abc.updateAbc(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
}