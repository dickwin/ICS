package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.TestMapper;
@Service
public class TestService {
    @Autowired
    private TestMapper test;
    
    public int addTest(Map paramMap){
        int ls=test.addTest(paramMap);
        return ls;
    }
    public int delTest(Map paramMap){
    	int ls= test.delTest(paramMap);
        return ls;
    }
    public int updateTest(Map paramMap){
    	int ls=test.updateTest(paramMap);
        return ls;
    }
    public List getTest(Map paramMap){
        List ls=test.getTest(paramMap);
        return ls;
    }
}