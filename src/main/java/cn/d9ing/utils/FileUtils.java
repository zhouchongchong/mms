/**  
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: FileUtils.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.common
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月27日 下午4:01:44
 * @version: V1.0.0
 */
package cn.d9ing.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;


/**
 * @ClassName: FileUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月27日 下午4:01:44
 */
public abstract class FileUtils {
	private static final String FILE_NAME_PREFIX = "d9ing_";
	private static final String TIMESTAMP_PATTERN = "yyyyMMddhhmmssSSS";
	private static final int RANDOM_NUMBER_LENGTH = 1000;

	static HashMap<String, String> extMap = null;
	static long maxSize = 0;
	static String saveDirectory = "";
	static {
		// 初始化上传文件配置
		extMap = new HashMap<String, String>();
		extMap.put("image", Configuration.getValue("system.media.image"));
		extMap.put("flash", Configuration.getValue("system.media.flash"));
		extMap.put("media", Configuration.getValue("system.media.media"));
		extMap.put("file", Configuration.getValue("system.media.file"));
		maxSize = Long
				.parseLong(Configuration.getValue("system.media.maxSize"));
		saveDirectory = Configuration.getValue("system.upload.preSavePath");
	}

	public static String getSavePath(String ext) {
		String fileType = "";
		Set<Entry<String, String>> ens = extMap.entrySet();
		for (Entry<String, String> en : ens) {
			int count = DataUtils.indexOf(ext, en.getValue().split(","));
			if (count > 0) {
				fileType = en.getKey();
				break;
			}
		}

		return getMaxSizeAndType(fileType);
	}

	/**
	 * 按照媒体类型获取上传大小限制
	 * 
	 * @param type
	 *            媒体类型
	 * @return 返回所上传到服务器的模块目录
	 */
	private static String getMaxSizeAndType(String type) {
		switch (type) {
		case "image":
			maxSize = Long.parseLong(Configuration
					.getValue("system.media.maxsize.imageSize"));
			return Configuration.getValue("system.upload.image.savePath");// 图片
		case "media":
			maxSize = Long.parseLong(Configuration
					.getValue("system.media.maxsize.mediaSize"));
			return Configuration.getValue("system.upload.media.savePath");// 视频
		case "flash":
			maxSize = Long.parseLong(Configuration
					.getValue("system.media.maxsize.flashSize"));
			return Configuration.getValue("system.upload.flash.savePath");// flash
		case "file":
			maxSize = Long.parseLong(Configuration
					.getValue("system.media.maxsize.fileSize"));
			return Configuration.getValue("system.upload.file.savePath");// 文件
		default:
			maxSize = Long.parseLong(Configuration
					.getValue("system.media.maxSize"));
			return Configuration.getValue("system.upload.default.savePath");
		}
	}

	/**
	 * @Title: reNameFile
	 * @Description: 对文件重命名
	 * @author: aiying010
	 * @return: String
	 * @param name
	 * @param ext
	 * @return
	 */
	public static String reNameFile(String name, String ext) {
		if (DataUtils.isBlank(name, ext)) {
			return DataUtils.getRandom(30) + "." + "d9ing";
		}
		return DataUtils.getRandom(30) + "." + ext;
	}

	/**
	 * 给指定文件按照系统要求重命名
	 * 
	 * @param file
	 *            文件名称，例如: a.jpg、b.wmv
	 * @return 重命名后的新文件名称
	 */
	public static final String createFileName(String file) {
		if (file == null || file.equals("") || file.length() == 0) {
			return "";
		}

		StringBuffer fileName = new StringBuffer();
		fileName.append(FILE_NAME_PREFIX);
		fileName.append(new SimpleDateFormat(TIMESTAMP_PATTERN)
				.format(new Date()));
		fileName.append("_");
		fileName.append(randomInt());
		fileName.append(".");
		fileName.append(getFileExtension(file));

		return fileName.toString();
	}

	/**
	 * 获取文件名称中的扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static final String getFileExtension(String fileName) {
		if (fileName == null || fileName.equals("") || fileName.length() == 0) {
			return "";
		}
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static final String randomInt() {
		return String.valueOf(new Random().nextInt(RANDOM_NUMBER_LENGTH));
	}

	/**
	 * @Title: createFileOnServer
	 * @Description: 获取服务器中要保存的路径
	 * @author: aiying010
	 * @return: String[] ：[0]代表服务器本地存储位置，[1]代表客户端访问地址;[2]代表文件存储名称
	 * @param fileName
	 *            文件名
	 * @param serverPath
	 *            服务器访问的url
	 * @return
	 */
	public static String[] createFileOnServer(String fileName, String serverPath) {
		String[] rst = new String[3];
		String fileExt = FileUtils.getFileExtension(fileName);
		String path = FileUtils.getSavePath(fileExt);
		// }
		String rename = FileUtils.reNameFile(DataUtils.getRandom(30), fileExt);
		String savePath = Keys.SYSTEM_REPOSITORY_PATH + FileUtils.saveDirectory
				+ path + "" + getDateTimePath(true);
		String remoteUrl = Keys.SERVER_FILE_ACCESS_PATH_PREFIX
				+ Keys.SOURCE_PREFIX + path + getDateTimePath(false) + rename;
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		// File outFile = new File(saveFile, rename);
		// outFile.
		rst[0] = savePath + rename;
		rst[1] = remoteUrl;
		rst[2] = rename;
		return rst;
	}

	/**
	 * @Title: createCodeOnServer
	 * @Description: 再服务器中生成鼎九码，
	 * @author: aiying010
	 * @return: String[] [0]代表服务器本地存储位置，[1]代表客户端访问地址;[2]代表文件存储名称
	 * @param fileName
	 *            文件名
	 * @param serverPath
	 *            服务器存储地址
	 * @return
	 */
	

	/**
	 * @Title: getDateTimePath
	 * @Description: 获取时间戳路径
	 * @author: aiying010
	 * @return: String
	 * @param revers
	 *            是否转换路径格式
	 * @return
	 */
	private static String getDateTimePath(boolean revers) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String year = ymd.substring(0, 4);
		String monuth = ymd.substring(4, 6);
		String day = ymd.substring(6, 8);
		if (revers) {
			return year + File.separator + monuth + File.separator + day
					+ File.separator;
		}
		return year + "/" + monuth + "/" + day + "/";
	}
}
