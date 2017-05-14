/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: LoggerUtils.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.log
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月26日 上午11:12:08
 * @version: V1.0.0
 */
package cn.d9ing.utils;

import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import cn.d9ing.utils.beans.DebugAppender;
import cn.d9ing.utils.beans.ErrorAppender;
import cn.d9ing.utils.beans.InfoAppender;



/**
 * @ClassName: LoggerUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月26日 上午11:12:08
 */
public class LoggerUtils {

	/**
	 * @Title: getLogger
	 * @Description: 获取Logger实例
	 * @author: aiying010
	 * @return: Logger
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {

		Logger logger = Logger.getLogger(clazz); // 生成新的Logger
		logger.removeAllAppenders(); // 清空Appender，特別是不想使用現存實例時一定要初期化
		logger.setLevel(Level.ALL); // 设定Logger級別。
		logger.setAdditivity(true); // 设定是否继承父Logger。默认为true，继承root输出；设定false后将不出书root。

		InfoAppender appender_info = new InfoAppender(); // 生成新的Appender
		ErrorAppender appender_error = new ErrorAppender(); // 生成新的Appender
		DebugAppender appender_debug = new DebugAppender(); // 生成新的Appender
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern("[%d{yyyy-MM-dd HH:mm:ss}] %p %l : %m%n"); // log的输出形式

		appender_info.setLayout(layout);
		appender_debug.setLayout(layout);
		appender_error.setLayout(layout);

		appender_debug.setFile(Keys.SYSTEM_LOG_REPOSITORY_PATH + "debug/"
				+ DateUtils.dateyyyyMMdd(new Date()) + ".log");
		appender_error.setFile(Keys.SYSTEM_LOG_REPOSITORY_PATH + "error/"
				+ DateUtils.dateyyyyMMdd(new Date()) + ".log");
		appender_info.setFile(Keys.SYSTEM_LOG_REPOSITORY_PATH + "info/"
				+ DateUtils.dateyyyyMMdd(new Date()) + ".log"); // log输出路径

		appender_error.setEncoding("UTF-8"); // log的字符编码
		appender_info.setEncoding("UTF-8"); // log的字符编码
		appender_debug.setEncoding("UTF-8"); // log的字符编码

		appender_debug.setAppend(true); // 日志合并方式： true:在已存在log文件后面追加
		appender_error.setAppend(true); // 日志合并方式： true:在已存在log文件后面追加
		appender_info.setAppend(true); // 日志合并方式： true:在已存在log文件后面追加

		appender_error.activateOptions(); // 适用当前配置
		appender_info.activateOptions(); // 适用当前配置
		appender_debug.activateOptions(); // 适用当前配置

		appender_error.setThreshold(Level.ERROR);
		appender_info.setThreshold(Level.INFO);
		appender_debug.setThreshold(Level.DEBUG);

		logger.addAppender(appender_error); // 将新的Appender加到Logger中
		logger.addAppender(appender_info); // 将新的Appender加到Logger中
		logger.addAppender(appender_debug); // 将新的Appender加到Logger中
		return logger;
	}

	/**
	 * 是否开启Debug
	 */
	public static boolean isDebug = LoggerUtils.getLogger(LoggerUtils.class)
			.isDebugEnabled();

	/**
	 * Debug 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 */
	public static void debug(Class<? extends Object> clazz, String message) {
		if (!isDebug)
			return;
		Logger logger = LoggerUtils.getLogger(clazz);
		logger.debug(message);
	}

	/**
	 * Debug 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public static void fmtDebug(Class<? extends Object> clazz,
			String fmtString, Object... value) {
		if (!isDebug)
			return;
		if (DataUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}

	/**
	 * Error 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 * @param e
	 *            异常类
	 */
	public static void error(Class<? extends Object> clazz, String message,
			Exception e) {
		Logger logger = LoggerUtils.getLogger(clazz);
		if (null == e) {
			logger.error(message);
			return;
		}
		logger.error(message, e);
	}

	/**
	 * @Title: info
	 * @Description: 输出info级别日志
	 * @author: aiying010
	 * @return: void
	 * @param clazz
	 *            目标 .class
	 * @param message
	 *            输出信息
	 * @param e
	 *            异常类
	 */
	public static void info(Class<? extends Object> clazz, String message,
			Exception e) {
		Logger logger = LoggerUtils.getLogger(clazz);
		if (null == e) {
			logger.info(message);
			return;
		}
		logger.info(message, e);
	}

	/**
	 * Error 输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param message
	 *            输出信息
	 */
	public static void error(Class<? extends Object> clazz, String message) {
		error(clazz, message, null);
	}

	/**
	 * @Title: info
	 * @Description: 打印info级别日志
	 * @author: aiying010
	 * @return: void
	 * @param clazz
	 * @param message
	 */
	public static void info(Class<? extends Object> clazz, String message) {
		info(clazz, message, null);
	}

	/**
	 * 异常填充值输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param e
	 *            异常类
	 * @param value
	 *            输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz, Exception e,
			String fmtString, Object... value) {
		if (DataUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}

	/**
	 * 异常填充值输出
	 * 
	 * @param clazz
	 *            目标.Class
	 * @param fmtString
	 *            输出信息key
	 * @param value
	 *            输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz,
			String fmtString, Object... value) {
		if (DataUtils.isBlank(fmtString)) {
			return;
		}
		if (null != value && value.length != 0) {
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}

}
