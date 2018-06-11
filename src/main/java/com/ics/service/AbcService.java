package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.AbcMapper;
@Service
public class AbcService {
    @Autowired
    private AbcMapper abc;
    
    public int addAbc(Map paramMap){
        int ls=abc.addAbc(paramMap);
        return ls;
    }
    public int delAbc(Map paramMap){
    	int ls= abc.delAbc(paramMap);
        return ls;
    }
    public int updateAbc(Map paramMap){
    	int ls=abc.updateAbc(paramMap);
        return ls;
    }
    public List getAbc(Map paramMap){
        List ls=abc.getAbc(paramMap);
        return ls;
    }
}