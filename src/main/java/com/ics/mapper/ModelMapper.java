package com.ics.mapper;

import java.util.List;
import java.util.Map;

public interface ModelMapper {
	public int addModel(Map paramMap);
	public int delModel(Map paramMap);
	public int updateModel(Map paramMap);
	public List getModel(Map paramMap);
	public List getDept(Map paramMap);
}