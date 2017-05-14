/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: DateUtils.java
 * @Prject: ded-utils
 * @Package: DateUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月3日 下午5:36:31
 * @version: V1.0  
 */
package cn.d9ing.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DateUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月3日 下午5:36:31
 */
public abstract class DateUtils {
	public static final String DATE_FORMAT_DATEPATH = "yyyy/MM/dd";
	public static final String MONTH_FORMAT_DATEPATH = "yyyy/MM";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_TIME_R = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_TIME_T = "yyyy-MM-dd HH:mm:ss";
	public static final String DB_TIME_PATTERN = "yyyyMMddHHmmss";
	public static final String DB_TIME_PATTERN_R = "yyyyMMdd";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSS = "yyyyMMddHHmmssSSS";

	private static final int INT_YEAR = 999;
	private static Map<Integer, Long> periods = null;

	/**
	 * 把Date格式的日期格式化成yyyy-MM-dd HH:mm:ss 字符串
	 * 
	 * @param date
	 *            需要格式化的Date
	 * @return 字符串 yyyy-MM-dd HH:mm:ss 异常返回当前日期格式化的字符串
	 */
	public static String dateyyyyMMddHHmmss(Date date) {
		if (date != null && !"".equals(date)) {
			return new SimpleDateFormat(DATE_FORMAT_TIME_T).format(date);
		} else {
			return null;
		}
	}

	/**
	 * @Title: dateyyyyMMddHHmmssSSS
	 * @Description: 返回yyyyMMddHHmmssSSS格式的时间戳
	 * @author: aiying010
	 * @return: String
	 * @param date
	 * @return
	 */
	public static String dateyyyyMMddHHmmssSSS(Date date) {
		if (date != null && !"".equals(date)) {
			return new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSSS)
					.format(date);
		} else {
			return null;
		}
	}

	/**
	 * @Title: dateyyyyMMddHHmmss
	 * @Description: 获取当前格式为yyyy-MM-dd HH:mm:ss的时间
	 * @author: aiying010
	 * @return: String
	 * @return
	 */
	public static String dateyyyyMMddHHmmss() {
		Date date = new Date();
		if (date != null && !"".equals(date)) {
			return new SimpleDateFormat(DATE_FORMAT_TIME_T).format(date);
		} else {
			return null;
		}
	}
	
	/**  
	 * sqlDate:数据库datetime时间. <br/>    
	 * @author zhouchong  
	 * @return  
	 * @since JDK 1.8  
	 */
	public static Date sqlDate(){
		Date data =  new Date();
		Timestamp timeStamp = new Timestamp(data.getTime());
		return timeStamp;
	}
	/**
	 * 把Date格式的日期格式化成yyyyMMdd 字符串
	 * 
	 * @param date
	 *            需要格式化的Date
	 * @return 字符串 yyyyMMdd 异常返回当前日期格式化的字符串
	 */
	public static String dateyyyyMMddR(Date date) {
		if (date != null && !"".equals(date)) {
			return new SimpleDateFormat(DB_TIME_PATTERN_R).format(date);
		} else {
			return null;
		}
	}

	/**
	 * 把Date格式的日期格式化成yyyy-MM-dd 字符串
	 * 
	 * @param date
	 *            需要格式化的Date
	 * @return 字符串 yyyy-MM-dd 异常返回当前日期格式化的字符串
	 */
	public static String dateyyyyMMdd(Date date) {
		if (date != null && !"".equals(date)) {
			return new SimpleDateFormat(DATE_FORMAT_YYYYMMDD).format(date);
		} else {
			return null;
		}
	}

	/**
	 * 把yyyy-MM-dd HH:mm:ss 字符串 格式的日期格式化成 Date
	 * 
	 * @param str
	 *            需要格式的字符串
	 * @return 异常返回当前Date
	 */
	public static Date parseDate(String str) {
		if (str != null && !"".equals(str)) {
			try {
				return new SimpleDateFormat(DATE_FORMAT_TIME_T).parse(str);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * 格式化日期格式
	 * 
	 * @param date
	 * @param format
	 * @return 格式化后的日期字符串
	 */
	public static String format(Date date, String format) {
		return date != null ? new SimpleDateFormat(format).format(date)
				.toString() : "";
	}

	/**
	 * 把字符串格式化成日期
	 * 
	 * @param argDateStr
	 * @return 格式化的日期
	 */
	public static Date parse(String argDateStr) {
		return parse(argDateStr, null);
	}

	/**
	 * 把字符串格式化成日期
	 * 
	 * @param argDateStr
	 * @param argFormat
	 * @return
	 */
	public static Date parse(String argDateStr, String argFormat) {
		if (argDateStr == null || argDateStr.trim().length() < 1) {
			return null;
		}

		SimpleDateFormat sdfFormat = null;
		Date result = null;

		try {
			String strFormat = argFormat;
			if (DataUtils.isNullOrEmpty(strFormat)) {
				strFormat = DATE_FORMAT_YYYYMMDD;
				if (argDateStr.length() > 16) {
					strFormat = DATE_FORMAT_TIME_T;
				} else if (argDateStr.length() > 10) {
					strFormat = DATE_FORMAT_TIME_R;
				}
			}
			sdfFormat = new SimpleDateFormat(strFormat);
			result = sdfFormat.parse(argDateStr);
		} catch (Exception e) {
			result = null;
		} finally {
			sdfFormat = null;
		}

		return result;
	}

	/**
	 * 根据特定的日期返回当天的星期数
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		String dayOfWeek = "";
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
		case 1:
			dayOfWeek = "星期日";
			break;
		case 2:
			dayOfWeek = "星期一";
			break;
		case 3:
			dayOfWeek = "星期二";
			break;
		case 4:
			dayOfWeek = "星期三";
			break;
		case 5:
			dayOfWeek = "星期四";
			break;
		case 6:
			dayOfWeek = "星期五";
			break;
		case 7:
			dayOfWeek = "星期六";
			break;
		}
		return dayOfWeek;
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @param date1
	 * @param date2
	 * @param format
	 * @return
	 */
	public static int compare(Date date1, Date date2, String format) {
		if (date1 == null && date2 == null) {
			return 0;
		}
		if (date1 == null) {
			return -1;
		}
		if (date2 == null) {
			return 1;
		}

		String strDate1 = format(date1, format);
		String strDate2 = format(date2, format);

		return strDate1.compareTo(strDate2);
	}

	/**
	 * 返回参数月的天数
	 * 
	 * @param argMonth
	 * @return
	 */
	public static long getMonthPeriod(int argMonth) {
		initPeriod();
		return periods.get(argMonth);
	}

	private static void initPeriod() {
		Calendar cal = Calendar.getInstance();
		if (periods != null
				&& periods.get(INT_YEAR).equals(cal.get(Calendar.YEAR))) {
			return;
		}
		periods = new HashMap<Integer, Long>();
		// now year
		periods.put(INT_YEAR, Long.valueOf(cal.get(Calendar.YEAR)));

		Calendar calNext = Calendar.getInstance();

		// JANUARY
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		// FEBRUARY
		calNext.set(Calendar.MONTH, Calendar.FEBRUARY);
		long lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: JANUARY
		periods.put(Calendar.JANUARY, lngDay);

		// MARCH
		cal.set(Calendar.MONTH, Calendar.MARCH);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: FEBRUARY
		periods.put(Calendar.FEBRUARY, lngDay);

		// APRIL
		calNext.set(Calendar.MONTH, Calendar.APRIL);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: MARCH
		periods.put(Calendar.MARCH, lngDay);

		// MAY
		cal.set(Calendar.MONTH, Calendar.MAY);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: APRIL
		periods.put(Calendar.APRIL, lngDay);

		// JUNE
		calNext.set(Calendar.MONTH, Calendar.JUNE);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: MAY
		periods.put(Calendar.MAY, lngDay);

		// JULY
		cal.set(Calendar.MONTH, Calendar.JULY);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: JUNE
		periods.put(Calendar.JUNE, lngDay);

		// AUGUST
		calNext.set(Calendar.MONTH, Calendar.AUGUST);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: JULY
		periods.put(Calendar.JULY, lngDay);

		// SEPTEMBER
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: AUGUST
		periods.put(Calendar.AUGUST, lngDay);

		// OCTOBER
		calNext.set(Calendar.MONTH, Calendar.OCTOBER);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: SEPTEMBER
		periods.put(Calendar.SEPTEMBER, lngDay);

		// NOVEMBER
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: OCTOBER
		periods.put(Calendar.OCTOBER, lngDay);

		// DECEMBER
		calNext.set(Calendar.MONTH, Calendar.DECEMBER);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: NOVEMBER
		periods.put(Calendar.NOVEMBER, lngDay);

		// next year JANUARY
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.add(Calendar.YEAR, 1);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: NOVEMBER
		periods.put(Calendar.NOVEMBER, lngDay);
	}

	/**
	 * 根据月份生成文件目录路径
	 * 
	 * @param date
	 *            日期
	 * @return yyyy/MM
	 */
	public static String getMonthPath(Date date) {
		return new SimpleDateFormat(MONTH_FORMAT_DATEPATH).format(date);
	}

	/**
	 * 根据日期生成文件目录路径
	 * 
	 * @param date
	 *            日期
	 * @return yyyy/MM/dd
	 */
	public static String getDatePath(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_DATEPATH).format(date);
	}

}
