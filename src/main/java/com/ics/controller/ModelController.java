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

import com.ics.service.ModelService;

@Controller
@RequestMapping("/model")
public class ModelController extends BaseController{
	private static Logger log = LogManager.getLogger(ModelController.class.getName());

	@Autowired
	private ModelService model;
    
	@RequestMapping(value = "/getModel", method = RequestMethod.GET)
	@ResponseBody
    public Object getModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			List rs = model.getModel(paramMap);
			data.put("data",rs);
			data.put("len",rs.size());
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return data;
    }
	
	@RequestMapping(value = "/addModel", method = RequestMethod.GET)
	@ResponseBody
    public Object addModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = model.addModel(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/delModel", method = RequestMethod.GET)
	@ResponseBody
    public Object delModel() {
		Map data = new HashMap();
		data.put("ret", "1");
		try {
			int rs = model.delModel(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/updateModel", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = model.updateModel(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
}
