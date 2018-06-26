package com.ics.mapper;

import java.util.List;
import java.util.Map;

public interface UserMapper {
	public int addUser(Map paramMap);
	public int delUser(Map paramMap);
	public int updateUser(Map paramMap);
	public List getUser(Map paramMap);
}