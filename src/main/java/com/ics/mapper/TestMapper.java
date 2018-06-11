package com.ics.mapper;

import java.util.List;
import java.util.Map;

public interface TestMapper {
	public int addTest(Map paramMap);
	public int delTest(Map paramMap);
	public int updateTest(Map paramMap);
	public List getTest(Map paramMap);
}