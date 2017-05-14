/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: StringUtils.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.string
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月25日 上午11:44:14
 * @version: V1.0.0
 */
package cn.d9ing.utils;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;


import sun.misc.*;

/**
 * @ClassName: StringUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月25日 上午11:44:14
 */
public class StringUtils {
	/**
	 * 一次性判断多个或单个对象为空。
	 * 
	 * @param objects
	 * @return 只要有一个元素为Blank，则返回true
	 */
	public static boolean isBlank(Object... objects) {
		Boolean result = false;
		for (Object object : objects) {
			if (null == object || "".equals(object.toString().trim())
					|| "null".equals(object.toString().trim())) {
				result = true;
				break;
			}
		}
		return result;
	}
	public static Integer strToInt(String numbers){
		char[] chars = numbers.toCharArray();
		Integer result = 0;
		for(char c:chars){
			String i = new String(new char[]{c});
			result = (result +Integer.parseInt(i))*10;
		}
		return result/10;
	}
	public static String getRandom(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val.toLowerCase();
	}
	
	public static String getRandom(int length,boolean upper) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}
		if(upper){
			return val.toUpperCase();
		}
		return val.toLowerCase();
	}

	/**
	 * 一次性判断多个或单个对象不为空。
	 * 
	 * @param objects
	 * @author zhou-baicheng
	 * @return 只要有一个元素不为Blank，则返回true
	 */
	public static boolean isNotBlank(Object... objects) {
		return !isBlank(objects);
	}

	public static boolean isBlank(String... objects) {
		Object[] object = objects;
		return isBlank(object);
	}

	public static boolean isNotBlank(String... objects) {
		Object[] object = objects;
		return !isBlank(object);
	}

	public static boolean isBlank(String str) {
		Object object = str;
		return isBlank(object);
	}

	public static boolean isNotBlank(String str) {
		Object object = str;
		return !isBlank(object);
	}

	/**
	 * 判断一个字符串在数组中存在几个
	 * 
	 * @param baseStr
	 * @param strings
	 * @return
	 */
	public static int indexOf(String baseStr, String[] strings) {

		if (null == baseStr || baseStr.length() == 0 || null == strings)
			return 0;

		int i = 0;
		for (String string : strings) {
			boolean result = baseStr.equals(string);
			i = result ? ++i : i;
		}
		return i;
	}


	public static String trimToEmpty(Object str) {
		return (isBlank(str) ? "" : str.toString().trim());
	}

	/**
	 * 将 Strig 进行 BASE64 编码
	 * 
	 * @param str
	 *            [要编码的字符串]
	 * @param bf
	 *            [true|false,true:去掉结尾补充的'=',false:不做处理]
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static String getBASE64(String str, boolean... bf) {
		if (StringUtils.isBlank(str))
			return null;
		String base64 = new BASE64Encoder().encode(str.getBytes());
		// 去掉 '='
		if (isBlank(bf) && bf[0]) {
			base64 = base64.replaceAll("=", "");
		}
		return base64;
	}

	/** 将 BASE64 编码的字符串 s 进行解码 **/
	@SuppressWarnings("restriction")
	public static String getStrByBASE64(String s) {
		if (isBlank(s))
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return "";
		}
	}
	/** 
	   * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	   * 
	   * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 
	   * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。 
	   * 
	   * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 
	   * 192.168.1.100 
	   * 
	   * 用户真实IP为： 192.168.1.110 
	   * 
	   * @param request 
	   * @return 
	   */
	  public static String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
}
