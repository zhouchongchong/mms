/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: UploadUtils.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.common
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年10月9日 上午10:22:37
 * @version: V1.0.0
 */
package cn.d9ing.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.d9ing.utils.beans.FileBean;



/**
 * @ClassName: UploadUtils
 * @Description: 文件上传保存工具类
 * @author: aiying010
 * @date: 2016年10月9日 上午10:22:37
 */
public class UploadUtils {
	private static final Logger logger = LoggerUtils
			.getLogger(UploadUtils.class);

	/**
	 * 单例模式
	 */
	private UploadUtils() {
	}

	private static UploadUtils uploadUtils = null;

	/**
	 * 获取工具类实例
	 * 
	 * @return
	 */
	public static UploadUtils getInstance() {
		if (uploadUtils == null) {
			return new UploadUtils();
		}
		return uploadUtils;
	}

	/**
	 * @Title: upload
	 * @Description: 文件上传
	 * @author: aiying010
	 * @return: FileBean
	 * @param request
	 * @param file
	 * @param path
	 * @return
	 */
	public FileBean upload(HttpServletRequest request,
			CommonsMultipartFile file, String path) {
		FileBean fileBean = null;
		try {
			String originalFileName = file.getOriginalFilename(); 						//文件名 eg: aa.jpeg
			fileBean = new FileBean();													
			String fileExt = FileUtils.getFileExtension(originalFileName);				//文件后缀名  eg:jpg
			fileBean.setType(fileExt);
			fileBean.setFileName(originalFileName);
			String[] fileInfo = FileUtils.createFileOnServer(originalFileName,"");

			File saveFile = new File(fileInfo[0]);
			file.transferTo(saveFile);
//			String smallPicture = PictureUtils.zipImageFileIO(fileInfo[0], 0.2F, "smallIcon");
//			PictureUtils.zipImageFile(oldFile, width, height, quality, smallIcon)
			String md5 = MD5Utils.getFileMD5String(saveFile);
			fileBean.setMd5(md5);
			fileBean.setLocalUrl(saveFile.getPath());
			fileBean.setLocalName(fileInfo[2]);
			fileBean.setRemoteUrl(fileInfo[1]);
			fileBean.setSize(file.getSize());
			fileBean.setUploadIp(request.getRemoteAddr());
			return fileBean;
		} catch (IllegalStateException e) {
			logger.error("上传文件失败：" + e.getMessage());
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("IO异常:" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Title: uploadVideo
	 * @Description: 上传视频
	 * @author: aiying010
	 * @return: FileBean
	 * @param request
	 * @param file
	 * @param path
	 * @return
	 */
//	public FileBean uploadVideo(HttpServletRequest request,
//			CommonsMultipartFile file, String path) {
//		FileBean fileBean = null;
//		try {
//			String originalFileName = file.getOriginalFilename();
//			fileBean = new VideoBean();
//			fileBean.setFileName(originalFileName);
//			String fileExt = FileUtils.getFileExtension(originalFileName);
//			// if(DataUtils.isBlank(path)){
//			path = FileUtils.getSavePath(fileExt);
//			// }
//
//			fileBean.setType(fileExt);
//			String rename = FileUtils.reNameFile(DataUtils.getRandom(30),
//					fileExt);
//			String savePath = Keys.SYSTEM_REPOSITORY_PATH
//					+ FileUtils.saveDirectory + path;
//			String remoteUrl = PathUtils.getRootPath(request) + ""
//					+ Keys.SOURCE_PREFIX + path + rename;
//			File saveFile = new File(savePath + rename);
//			if (!saveFile.exists()) {
//				saveFile.mkdirs();
//			}
//			file.transferTo(saveFile);
//			String md5 = MD5Utils.getFileMD5String(saveFile);
//			fileBean.setMd5(md5);
//			fileBean.setLocalUrl(saveFile.getPath());
//			fileBean.setLocalName(rename);
//			fileBean.setRemoteUrl(remoteUrl);
//			fileBean.setSize(file.getSize());
//			fileBean.setUploadIp(request.getRemoteAddr());
//			return fileBean;
//		} catch (IllegalStateException e) {
//			logger.error("上传文件失败：" + e.getMessage());
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//			logger.error("IO异常:" + e.getMessage());
//			throw new RuntimeException(e);
//		}
//	}

}
