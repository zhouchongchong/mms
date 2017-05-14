/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: VideoBean.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utilsl.file
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年10月20日 下午5:09:38
 * @version: V1.0  
 */
package cn.d9ing.utils.beans;

/**
 * @ClassName: VideoBean
 * @Description: 视频模型
 * @author: aiying010
 * @date: 2016年10月20日 下午5:09:38
 */
public class VideoBean extends FileBean {
	/**
	 * @fieldName: cover
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 视频封面
	 */
	private String cover;
	/**
	 * @fieldName: videoTime
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 视频时长
	 */
	private String videoTime;
	/**
	 * @fieldName: videoType
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 视频类型：演唱者的视频？晚会介绍？
	 */
	private String videoType;
	// 视频时
	private int hours;
	// 视频分
	private int minutes;
	// 视频秒
	private float seconds;
	// 视频width
	private int width;
	// 视频height
	private int heigt;

	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover
	 *            the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * @return the videoTime
	 */
	public String getVideoTime() {
		return videoTime;
	}

	/**
	 * @param videoTime
	 *            the videoTime to set
	 */
	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	/**
	 * @return the videoType
	 */
	public String getVideoType() {
		return videoType;
	}

	/**
	 * @param videoType
	 *            the videoType to set
	 */
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes
	 *            the minutes to set
	 */
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the seconds
	 */
	public float getSeconds() {
		return seconds;
	}

	/**
	 * @param seconds
	 *            the seconds to set
	 */
	public void setSeconds(float seconds) {
		this.seconds = seconds;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the heigt
	 */
	public int getHeigt() {
		return heigt;
	}

	/**
	 * @param heigt
	 *            the heigt to set
	 */
	public void setHeigt(int heigt) {
		this.heigt = heigt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @author: aiying010
	 */
	@Override
	public String toString() {
		return "VideoBean [cover=" + cover + ", videoTime=" + videoTime
				+ ", videoType=" + videoType + ", hours=" + hours
				+ ", minutes=" + minutes + ", seconds=" + seconds + ", width="
				+ width + ", heigt=" + heigt + "]";
	}

//	public VideoBean getInfo(String videoFilename) throws IOException,
//			InterruptedException {
//		String tmpFile = videoFilename + ".tmp.png";
//		ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y",
//				"-i", videoFilename, "-vframes", "1", "-ss", "0:0:0", "-an",
//				"-vcodec", "png", "-f", "rawvideo", "-s", "100*100", tmpFile);
//
//		Process process = processBuilder.start();
//
//		InputStream stderr = process.getErrorStream();
//		InputStreamReader isr = new InputStreamReader(stderr);
//		BufferedReader br = new BufferedReader(isr);
//		String line;
//		// 打印 sb，获取更多信息。 如 bitrate、width、heigt
//		StringBuffer sb = new StringBuffer();
//		while ((line = br.readLine()) != null) {
//			sb.append(line);
//		}
//
//		new File(tmpFile).delete();
//
//		Pattern pattern = Pattern.compile("Duration: (.*?),");
//		Matcher matcher = pattern.matcher(sb);
//
//		if (matcher.find()) {
//			String time = matcher.group(1);
//			calcTime(time);
//		}
//
//		pattern = Pattern.compile("w:\\d+ h:\\d+");
//		matcher = pattern.matcher(sb);
//
//		if (matcher.find()) {
//			String wh = matcher.group();
//			// w:100 h:100
//			String[] strs = wh.split("\\s+");
//			if (strs != null && strs.length == 2) {
//				width = Integer.parseInt(strs[0].split(":")[1]);
//				heigt = Integer.parseInt(strs[1].split(":")[1]);
//			}
//		}
//
//		process.waitFor();
//		if (br != null)
//			br.close();
//		if (isr != null)
//			isr.close();
//		if (stderr != null)
//			stderr.close();
//		return this;
//	}

	private void calcTime(String timeStr) {
		String[] parts = timeStr.split(":");
		hours = Integer.parseInt(parts[0]);
		minutes = Integer.parseInt(parts[1]);
		seconds = Float.parseFloat(parts[2]);
	}

	public void parseVideoTime(int hour, int min, float sec) {
		StringBuffer sb = new StringBuffer();
		if (hour != 0) {
			sb.append(hour + ":");
		}
		sb.append(min + ":");
		sb.append((int) sec);
		this.videoTime = sb.toString();
	}
}
