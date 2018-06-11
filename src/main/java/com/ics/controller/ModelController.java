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
import com.ics.vo.Result;

@Controller
@RequestMapping("/model")
public class ModelController extends BaseController{
	private static Logger log = LogManager.getLogger(ModelController.class.getName());

	@Autowired
	private ModelService model;
    
	@RequestMapping(value = "/getModel", method = RequestMethod.GET)
	@ResponseBody
    public Object getModel() {
		Result result = new Result(1, null);
		try {
			List rs = model.getModel(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return result;
    }
	
	@RequestMapping(value = "/addModel", method = RequestMethod.GET)
	@ResponseBody
    public Object addModel() {
		Result result = new Result(1, null);
		try {
			int rs = model.addModel(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/delModel", method = RequestMethod.GET)
	@ResponseBody
    public Object delModel() {
		Result result = new Result(1, null);
		try {
			int rs = model.delModel(paramMap);
			Map data = new HashMap();
			data.put("list", rs);
			result.setData(data);
			result.setRet(0);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return result;
    }
	
	@RequestMapping(value = "/updateModel", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Result result = new Result(1, null);
		try {
			int rs = model.updateModel(paramMap);
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
