/**
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: Keys.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.system
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月7日 下午6:30:19
 * @version: V1.0
 */
package cn.d9ing.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName: Keys
 * @Description: 系统键值工具类
 * @author: aiying010
 * @date: 2016年9月7日 下午6:30:19
 */
@Component
public class Keys implements Serializable {
    private static final Logger logger = LoggerUtils.getLogger(Keys.class);

    /**
     * @fieldName: serialVersionUID
     * @fieldType: long
     * @author: aiying010
     * @Description: TODO
     */
    private static final long serialVersionUID = -8022522800647222317L;

    /**
     * @fieldName: SYSTEM_REPOSITORY_PATH
     * @fieldType: String
     * @author: aiying010
     * @Description: 系统仓库存储位置
     */
    public static String SYSTEM_REPOSITORY_PATH = "";
    public static Integer IS_NOT_DELETE = 0;
    public static Integer IS_DELETE = 1;

    /**
     * @fieldName: SYSTEM_LOG_REPOSITORY_PATH
     * @fieldType: String
     * @author: aiying010
     * @Description: 系统日志仓库存储位置
     */
    public static String SYSTEM_LOG_REPOSITORY_PATH = "";

    /**
     * @fieldName: SOURCE_PREFIX
     * @fieldType: String
     * @author: aiying014
     * @Description: 支付通知结果地址
     */
//    public static final String payNoticeUrl = Configuration.getValue("system.account.payNoticeUrl");
    /**
     * @fieldName: payNoticePara
     * @fieldType: String
     * @author: aiying014
     * @Description: 成功支付附加参数
     */
//    public static final String payNoticePara = Configuration.getValue("system.account.payNoticePara");
    /**
     * @fieldName: returnUrl
     * @fieldType: String
     * @author: aiying014
     * @Description: 成功页返回商户地址, 支付成功页面上“返回商户”按钮跳转地址。
     */
//    public static final String returnUrl = Configuration.getValue("system.account.returnUrl");
    /**
     * @fieldName: signNoticeUrl
     * @fieldType: String
     * @author: aiying014
     * @Description: 成功签约结果通知地址
     */
//    public static final String signNoticeUrl = Configuration.getValue("system.account.signNoticeUrl");
    /**
     * @fieldName: expireTimeSpan
     * @fieldType: String
     * @author: aiying014
     * @Description: 过渡时间跨度
     */
//    public static final String expireTimeSpan = Configuration.getValue("system.account.expireTimeSpan");
    public static final String SOURCE_PREFIX = Configuration
            .getValue("system.source.prefix");

    /**
     * @fieldName: SYSTEM_CHARSET
     * @fieldType: String
     * @author: aiying010
     * @Description: 系统utf-8编码
     */
    public static final String SYSTEM_CHARSET = Configuration
            .getValue("system.charset");
    /**
     * @fieldName: USER_SESSION_NAME
     * @fieldType: String
     * @author: aiying010
     * @Description: 用户session名称
     */
    public static final String USER_SESSION_NAME = "USER_INFO_MAP";

    /**
     * @fieldName: USER_SESSION_RANDOM_CODE_NAME
     * @fieldType: String
     * @author: aiying010
     * @Description: 用户session里的短信验证码属性名
     */
    public static final String USER_SESSION_RANDOM_CODE_NAME = "random";
    /**
     * @fieldName: USER_SESSION_PHONE_NAME
     * @fieldType: String
     * @author: aiying010
     * @Description: 用户session里的用户手机号属性名
     */
    public static final String USER_SESSION_PHONE_NAME = "user_phone";
    /**
     * @fieldName: SERVER_FILE_ACCESS_PATH_PREFIX
     * @fieldType: String
     * @author: aiying010
     * @Description: 服务器访问路径
     */
    public static final String SERVER_FILE_ACCESS_PATH_PREFIX = Configuration
            .getValue("server.file.path");
    /**
     * app用户的请求的用户唯一标识字段名
     */
//    public static final String APP_USER_UID_KEY = "$uid$";
    /**
     * app用户的请求的用户TOKEN字段名
     */
    public static final String APP_USER_TOKEN_KEY = "token";
    /**
     * @fieldName: OS_NAME
     * @fieldType: String
     * @author: aiying010
     * @Description: 系统名称
     */
    public static String OS_NAME = "";
    /**
     * @fieldName: CODE_REPOSITORY
     * @fieldType: String
     * @author: aiying010
     * @Description: 生成的鼎九码存储位置
     */

    /**
     * @fieldName: RESOURCES_FFMPEG_PATH
     * @fieldType: String
     * @author: aiying010
     * @Description: ffmpeg插件的存储位置
     */
//    public static String RESOURCES_FFMPEG_PATH = "";
    /**
     * @fieldName: ACCOUNT_BRANCHNO
     * @fieldType: String
     * @author: aiying010
     * @Description: 商户分行号
     */
//    public static String ACCOUNT_BRANCHNO = Configuration
//            .getValue("system.account.branchNo");

    /**
     * @fieldName: ACCOUNT_MERCHANTNO
     * @fieldType: String
     * @author: aiying010
     * @Description: 商户号
     */
//    public static String ACCOUNT_MERCHANTNO = Configuration
//            .getValue("system.account.merchantNo");
    /**
     * @fieldName: TOKEN
     * @fieldType: String
     * @author: aiying010
     * @Description: 用户请求令牌
     */
    public static String TOKEN = UUID.randomUUID().toString().toUpperCase();

    static {
        OS_NAME = System.getProperty("os.name");
        LoggerUtils.info(Keys.class,
                DataUtils.appendString("系统类型为 ： ", OS_NAME));
        try {
            getRepository();
        } catch (Exception e) {
            logger.error("初始化出错 ： " + e.getMessage());
        }
    }

    /**
     * @Title: getRepository
     * @Description: 按照系统类型，获取系统中配置的路径
     * @author: aiying010
     * @return: void
     */
    private static void getRepository() throws Exception {
        try {
            int type = getOSType(OS_NAME);
            switch (type) {
                case SYSTEM_WIN:
                    SYSTEM_REPOSITORY_PATH = Configuration
                            .getValue("system.upload.window.repository");// 配置windows上传仓库
                    SYSTEM_LOG_REPOSITORY_PATH = Configuration
                            .getValue("system.log.windows.savepath");// 配置windows日志仓库
//                    RESOURCES_FFMPEG_PATH = PathUtils.getClassPath(
//                            "video-bin-resources/ffmpeg.exe", VideoUtils.class);// 配置windows内ffmpeg插件存在位置
                    break;
                case SYSTEM_LINUX:
                    SYSTEM_REPOSITORY_PATH = Configuration
                            .getValue("system.upload.linux.repository");// 配置Linux上传仓库
                    SYSTEM_LOG_REPOSITORY_PATH = Configuration
                            .getValue("system.log.linux.savepath");// 配置Linux日志仓库
//                    RESOURCES_FFMPEG_PATH = Configuration.getValue("ffmpeg.linux.path");// 配置Linux下ffmpeg插件存在位置
                    break;
                case -1:
                    SYSTEM_REPOSITORY_PATH = "";
                    SYSTEM_LOG_REPOSITORY_PATH = "";
                    break;
            }
        } catch (Exception e) {
            logger.error("多平台配置初始化时出错： " + e.getMessage());
        }

    }

    /**
     * @param name
     *
     * @return
     *
     * @Title: getOSType
     * @Description: 获取系统类型
     * @author: aiying010
     * @return: int
     */
    public static int getOSType(String name) {
        if (name.indexOf("Windows") != -1) {
            return SYSTEM_WIN;
        } else if (name.indexOf("Linux") != -1) {
            return SYSTEM_LINUX;
        }
        return -1;
    }

    /**
     * linux系统
     */
    public static final int SYSTEM_LINUX = 1;
    /**
     * windows系统
     */
    public static final int SYSTEM_WIN = 0;


}
