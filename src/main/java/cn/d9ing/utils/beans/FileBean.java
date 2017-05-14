/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: FileBean.java
 * @Prject: ded-model
 * @Package: com.d9ing.ded.model.file
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年10月12日 下午1:37:00
 * @version: V1.0  
 */
package cn.d9ing.utils.beans;

import java.util.Map;



/**
 * @ClassName: FileBean
 * @Description: 文件封装bean
 * @author: aiying010
 * @date: 2016年10月12日 下午1:37:00
 */
public class FileBean {
	/**
	 * @fieldName: fileName
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 原始文件名
	 */
	private String fileName;
	/**
	 * @fieldName: localName
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 本地存储的文件名
	 */
	private String localName;
	/**
	 * @fieldName: localUrl
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 本地存储位置
	 */
	private String localUrl;
	/**
	 * @fieldName: remoteUrl
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 远程访问地址
	 */
	private String remoteUrl;
	/**
	 * @fieldName: size
	 * @fieldType: Long
	 * @author: aiying010
	 * @Description: 文件大小
	 */
	private Long size;
	/**
	 * @fieldName: type
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 文件类型
	 */
	private String type;
	/**
	 * @fieldName: md5
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 文件md5
	 */
	private String md5;
	/**
	 * @fieldName: uploadIp
	 * @fieldType: String
	 * @author: aiying010
	 * @Description: 上传ip
	 */
	private String uploadIp;

	/**
	 * @return the uploadIp
	 */
	public String getUploadIp() {
		return uploadIp;
	}

	/**
	 * @param uploadIp
	 *            the uploadIp to set
	 */
	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	/**
	 * @return the md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 *            the md5 to set
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @Title:FileBean
	 * @Description:TODO
	 * @author: aiying010
	 * @param fileName
	 * @param localName
	 * @param localUrl
	 * @param remoteUrl
	 * @param size
	 * @param type
	 */
	public FileBean(String fileName, String localName, String localUrl,
			String remoteUrl, Long size, String type) {
		super();
		this.fileName = fileName;
		this.localName = localName;
		this.localUrl = localUrl;
		this.remoteUrl = remoteUrl;
		this.size = size;
		this.type = type;
	}

	/**
	 * @Title:FileBean
	 * @Description:TODO
	 * @author: aiying010
	 */
	public FileBean() {
		super();
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the localName
	 */
	public String getLocalName() {
		return localName;
	}

	/**
	 * @param localName
	 *            the localName to set
	 */
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	/**
	 * @return the localUrl
	 */
	public String getLocalUrl() {
		return localUrl;
	}

	/**
	 * @param localUrl
	 *            the localUrl to set
	 */
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	/**
	 * @return the remoteUrl
	 */
	public String getRemoteUrl() {
		return remoteUrl;
	}

	/**
	 * @param remoteUrl
	 *            the remoteUrl to set
	 */
	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	/**
	 * @return the size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
		return "FileBean [fileName=" + fileName + ", localName=" + localName
				+ ", localUrl=" + localUrl + ", remoteUrl=" + remoteUrl
				+ ", size=" + size + ", type=" + type + ", md5=" + md5
				+ ", uploadIp=" + uploadIp + "]";
	}

}
