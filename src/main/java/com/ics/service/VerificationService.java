package com.ics.service;

import java.security.MessageDigest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ics.exception.GfTokenErrorException;

@Service
public class VerificationService {
	private static Logger logger = Logger.getLogger(VerificationService.class);
	
	//app id
	@Value("${AllowAppId:gfzqapp}")
	private String allowAppId;
	
	//app secret
	@Value("${AppSecret:77b262a5aa1f2ab6b198f71c2d5d246e}")
	private String secret;
	
	//token最长允许超前时间
	@Value("${TokenMaxAhead:600000}")
	private Long tokenMaxAhead;
	
	//token过期时间
	@Value("${TokenMaxExpire:600000}")
	private Long tokenMaxExpire;

	public int verifyToken(String cust_id, String appId, long timestamp, String token){
		long now = System.currentTimeMillis();
		if(timestamp >= now + tokenMaxAhead){
			return GfTokenErrorException.TOKEN_AHEAD_TOO_MUCH;
		}
		
		if(timestamp <= now - tokenMaxExpire){
			return GfTokenErrorException.TOKEN_EXPIRE;
		}
		
		if(!appId.equals(allowAppId)){
			return GfTokenErrorException.APPID_NOT_EXISTS;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(appId).append("|")
			.append(cust_id).append("|")
			.append(String.valueOf(timestamp)).append("|")
			.append(secret);
		
		logger.debug("String is" + sb.toString() + ", md5 is" + getMD5(sb.toString()) + ", token is" + token);	
		
		if(!getMD5(sb.toString()).equalsIgnoreCase(token)){
			return GfTokenErrorException.TOKEN_CHECKSUM_ERROR;
		}
		
		return 0;
	}
	
	public static String getMD5(String message) {
		String md5str = "";
		try {
			// 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 2 将消息变成byte数组
			byte[] input = message.getBytes();

			// 3 计算后获得字节数组,这就是那128位了
			byte[] buff = md.digest(input);

			// 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
			md5str = bytesToHex(buff);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}

	/**
	 * 二进制转十六进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];

			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}
}
