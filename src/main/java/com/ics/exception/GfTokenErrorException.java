package com.ics.exception;

public class GfTokenErrorException extends Exception {

	/**  
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)  
	*/  
	
	private static final long serialVersionUID = -3709978886101413149L;
	
	public static final int TOKEN_AHEAD_TOO_MUCH = 100;
	public static final int TOKEN_EXPIRE = 200;
	public static final int TOKEN_CHECKSUM_ERROR = 300;
	public static final int APPID_NOT_EXISTS = 400;
	
	private int verifyResult;
	
	public GfTokenErrorException(int verifyResult){
		this.setVerifyResult(verifyResult);
	}

	public int getVerifyResult() {
		return verifyResult;
	}

	public void setVerifyResult(int verifyResult) {
		this.verifyResult = verifyResult;
	}
	
	public String getMessage(){
		switch(verifyResult){
		case TOKEN_AHEAD_TOO_MUCH:
			return "token时间超前";
		case TOKEN_EXPIRE:
			return "token过期";
		case TOKEN_CHECKSUM_ERROR:
			return "token校验失败";
		case APPID_NOT_EXISTS:
			return "appId不存在";
		default:
			return "token异常";
		}
	}

}
