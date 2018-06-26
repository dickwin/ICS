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

import com.ics.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	private static Logger log = LogManager.getLogger(UserController.class.getName());

	@Autowired
	private UserService user;
    
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
    public Object getUser() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			List rs = user.getUser(paramMap);
			data.put("data",rs);
			data.put("len",rs.size());
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

        return data;
    }
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	@ResponseBody
    public Object addUser() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = user.addUser(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/delUser", method = RequestMethod.GET)
	@ResponseBody
    public Object delUser() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = user.delUser(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	@ResponseBody
    public Object updateModel() {
		Map data = new HashMap();
		data.put("ret","1");
		try {
			int rs = user.updateUser(paramMap);
			data.put("data", rs);
			data.put("ret","0");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}
        return data;
    }
}