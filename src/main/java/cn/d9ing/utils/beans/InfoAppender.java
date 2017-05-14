package cn.d9ing.utils.beans;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * 控制输出Debug级别的日志文件
 * 
 * @author jxx 2016年7月4日19:05:13
 *
 */
public class InfoAppender extends DailyRollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		// TODO Auto-generated method stub
		return this.getThreshold().equals(priority);
	}
}
