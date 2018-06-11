package com.ics.vo;

import java.util.Map;

public class Result {
	private int ret;
	private Map data;
	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}

	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	public Result(int ret, Map data) {
		super();
		this.ret = ret;
		this.data = data;
	}
	
}
