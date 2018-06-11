package com.ics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ics.mapper.TemplateMapper;
@Service
public class TemplateService {
    @Autowired
    private TemplateMapper Template;
    
    public int addTemplate(Map paramMap){
        int ls=Template.addTemplate(paramMap);
        return ls;
    }
    public int delTemplate(Map paramMap){
    	int ls= Template.delTemplate(paramMap);
        return ls;
    }
    public int updateTemplate(Map paramMap){
    	int ls=Template.updateTemplate(paramMap);
        return ls;
    }
    public List getTemplate(Map paramMap){
        List ls=Template.getTemplate(paramMap);
        return ls;
    }
    public List getTableColumn(Map paramMap){
        List ls=Template.getTableColumn(paramMap);
        return ls;
    }
}
