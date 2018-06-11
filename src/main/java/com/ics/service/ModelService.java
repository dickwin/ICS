package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.ModelMapper;
@Service
public class ModelService {
    @Autowired
    private ModelMapper model;
    
    public int addModel(Map paramMap){
        int ls=model.addModel(paramMap);
        return ls;
    }
    public int delModel(Map paramMap){
    	int ls= model.delModel(paramMap);
        return ls;
    }
    public int updateModel(Map paramMap){
    	int ls=model.updateModel(paramMap);
        return ls;
    }
    public List getModel(Map paramMap){
        List ls=model.getModel(paramMap);
        return ls;
    }
}
