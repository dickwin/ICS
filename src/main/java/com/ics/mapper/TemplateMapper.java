package com.ics.mapper;

import java.util.List;
import java.util.Map;

public interface TemplateMapper {
	public int addTemplate(Map paramMap);
	public int delTemplate(Map paramMap);
	public int updateTemplate(Map paramMap);
	public List getTemplate(Map paramMap);
	public List getTableColumn(Map paramMap);
}