package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.UserMapper;
@Service
public class UserService {
    @Autowired
    private UserMapper user;
    
    public int addUser(Map paramMap){
        int ls=user.addUser(paramMap);
        return ls;
    }
    public int delUser(Map paramMap){
    	int ls= user.delUser(paramMap);
        return ls;
    }
    public int updateUser(Map paramMap){
    	int ls=user.updateUser(paramMap);
        return ls;
    }
    public List getUser(Map paramMap){
        List ls=user.getUser(paramMap);
        return ls;
    }
}