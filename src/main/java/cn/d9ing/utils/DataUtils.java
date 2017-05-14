/**
 * Copyright © 2016北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: DataUtils.java
 * @Prject: ded-utils
 * @Package: com.d9ing.ded.utils.common
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月26日 上午11:10:27
 * @version: V1.0.0
 */
package cn.d9ing.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: DataUtils
 * @Description: TODO
 * @author: aiying010
 * @date: 2016年9月26日 上午11:10:27
 */
public class DataUtils {

    /**
     * 一次性判断多个或单个对象为空。
     *
     * @param objects
     *
     * @return 只要有一个元素为Blank，则返回true
     *
     * @author zhou-baicheng
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

    /**
     * 一次性判断多个或单个对象不为空。
     *
     * @param objects
     *
     * @return 只要有一个元素不为Blank，则返回true
     *
     * @author zhou-baicheng
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
     *
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

    /**
     * 判断一个字符串是否为JSONObject,是返回JSONObject,不是返回null
     *
     * @param args
     *
     * @return
     */
    public static JSONObject isJSONObject(String args) {
        JSONObject result = null;
        if (isBlank(args)) {
            return result;
        }
        try {
            return JSONObject.parseObject(args.trim());
        } catch (Exception e) {
            return result;
        }
    }

    /**
     * 判断一个字符串是否为JSONArray,是返回JSONArray,不是返回null
     *
     * @param args
     *
     * @return
     */
    public static JSONArray isJSONArray(Object args) {
        JSONArray result = new JSONArray();
        if (isBlank(args)) {
            return null;
        }
        if (args instanceof JSONArray) {

            JSONArray arr = (JSONArray) args;
            for (Object json : arr) {
                if (json != null && json instanceof JSONObject) {
                    result.add(json);
                    continue;
                } else {
                    result.add(JSONObject.parse(json.toString()));
                }
            }
            return result;
        } else {
            return null;
        }

    }

    public static String trimToEmpty(Object str) {
        return (isBlank(str) ? "" : str.toString().trim());
    }

    /**
     * 将 Strig 进行 BASE64 编码
     *
     * @param str [要编码的字符串]
     * @param bf  [true|false,true:去掉结尾补充的'=',false:不做处理]
     *
     * @return
     */
    @SuppressWarnings("restriction")
    public static String getBASE64(String str, boolean... bf) {
        if (StringUtils.isBlank(str))
            return null;
        String base64 = new sun.misc.BASE64Encoder().encode(str.getBytes());
        // 去掉 '='
        if (isBlank(bf) && bf[0]) {
            base64 = base64.replaceAll("=", "");
        }
        return base64;
    }

    /**
     * 将 BASE64 编码的字符串 s 进行解码
     **/
    @SuppressWarnings("restriction")
    public static String getStrByBASE64(String s) {
        if (isBlank(s))
            return "";
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 把Map转换成get请求参数类型,如 {"name"=20,"age"=30} 转换后变成 name=20&age=30
     *
     * @param map
     *
     * @return
     */
    public static String mapToGet(Map<? extends Object, ? extends Object> map) {
        String result = "";
        if (map == null || map.size() == 0) {
            return result;
        }
        Set<? extends Object> keys = map.keySet();
        for (Object key : keys) {
            result += ((String) key + "=" + (String) map.get(key) + "&");
        }

        return isBlank(result) ? result : result.substring(0,
                result.length() - 1);
    }

    /**
     * 把一串参数字符串,转换成Map 如"?a=3&b=4" 转换为Map{a=3,b=4}
     *
     * @param args
     *
     * @return
     */
    public static Map<String, ? extends Object> getToMap(String args) {
        if (isBlank(args)) {
            return null;
        }
        args = args.trim();
        // 如果是?开头,把?去掉
        if (args.startsWith("?")) {
            args = args.substring(1, args.length());
        }
        String[] argsArray = args.split("&");

        Map<String, Object> result = new HashMap<String, Object>();
        for (String ag : argsArray) {
            if (!isBlank(ag) && ag.indexOf("=") > 0) {

                String[] keyValue = ag.split("=");
                // 如果value或者key值里包含 "="号,以第一个"="号为主 ,如 name=0=3
                // 转换后,{"name":"0=3"}, 如果不满足需求,请勿修改,自行解决.

                String key = keyValue[0];
                String value = "";
                for (int i = 1; i < keyValue.length; i++) {
                    value += keyValue[i] + "=";
                }
                value = value.length() > 0 ? value.substring(0,
                        value.length() - 1) : value;
                result.put(key, value);

            }
        }

        return result;
    }

    /**
     * 转换成Unicode
     *
     * @param str
     *
     * @return
     */
    public static String toUnicode(String str) {
        String as[] = new String[str.length()];
        String s1 = "";
        for (int i = 0; i < str.length(); i++) {
            int v = str.charAt(i);
            if (v >= 19968 && v <= 171941) {
                as[i] = Integer.toHexString(str.charAt(i) & 0xffff);
                s1 = s1 + "\\u" + as[i];
            } else {
                s1 = s1 + str.charAt(i);
            }
        }
        return s1;
    }

    /**
     * 合并数据
     *
     * @param v
     *
     * @return
     */
    public static String merge(Object... v) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
        }
        return sb.toString();
    }

    /**
     * 字符串转urlcode
     *
     * @param value
     *
     * @return
     */
    public static String strToUrlcode(String value) {
        try {
            value = java.net.URLEncoder.encode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            LoggerUtils.error(StringUtils.class, "字符串转换为URLCode失败,value:"
                    + value, e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * urlcode转字符串
     *
     * @param value
     *
     * @return
     */
    public static String urlcodeToStr(String value) {
        try {
            value = java.net.URLDecoder.decode(value, "utf-8");
            return value;
        } catch (UnsupportedEncodingException e) {
            LoggerUtils.error(StringUtils.class, "URLCode转换为字符串失败;value:"
                    + value, e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断字符串是否包含汉字
     *
     * @param txt
     *
     * @return
     */
    public static Boolean containsCN(String txt) {
        if (isBlank(txt)) {
            return false;
        }
        for (int i = 0; i < txt.length(); i++) {

            String bb = txt.substring(i, i + 1);

            boolean cc = java.util.regex.Pattern.matches("[\u4E00-\u9FA5]", bb);
            if (cc)
                return cc;
        }
        return false;
    }

    /**
     * 去掉HTML代码
     *
     * @param news
     *
     * @return
     */
    public static String removeHtml(String news) {
        String s = news.replaceAll("amp;", "").replaceAll("<", "<")
                .replaceAll(">", ">");

        Pattern pattern = Pattern.compile(
                "<(span)?\\sstyle.*?style>|(span)?\\sstyle=.*?>",
                Pattern.DOTALL);
        Matcher matcher = pattern.matcher(s);
        String str = matcher.replaceAll("");

        Pattern pattern2 = Pattern.compile("(<[^>]+>)", Pattern.DOTALL);
        Matcher matcher2 = pattern2.matcher(str);
        String strhttp = matcher2.replaceAll(" ");

        String regEx = "(((http|https|ftp)(\\s)*((\\:)|：))(\\s)*(//|//)(\\s)*)?"
                + "([\\sa-zA-Z0-9(\\.|．)(\\s)*\\-]+((\\:)|(:)[\\sa-zA-Z0-9(\\.|．)&%\\$\\-]+)*@(\\s)*)?"
                + "("
                + "(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)"
                + "(\\.|．)(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])"
                + "|([\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*)*[\\sa-zA-Z0-9\\-]+(\\.|．)(\\s)*[\\sa-zA-Z]*"
                + ")"
                + "((\\s)*(\\:)|(：)(\\s)*[0-9]+)?"
                + "(/(\\s)*[^/][\\sa-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*";
        Pattern p1 = Pattern.compile(regEx, Pattern.DOTALL);
        Matcher matchhttp = p1.matcher(strhttp);
        String strnew = matchhttp.replaceAll("").replaceAll(
                "(if[\\s]*\\(|else|elseif[\\s]*\\().*?;", " ");

        Pattern patterncomma = Pattern.compile("(&[^;]+;)", Pattern.DOTALL);
        Matcher matchercomma = patterncomma.matcher(strnew);
        String strout = matchercomma.replaceAll(" ");
        String answer = strout.replaceAll("[\\pP‘’“”]", " ")
                .replaceAll("\r", " ").replaceAll("\n", " ")
                .replaceAll("\\s", " ").replaceAll("　", "");

        return answer;
    }

    /**
     * 把数组的空数据去掉
     *
     * @param array
     *
     * @return
     */
    public static List<String> array2Empty(String[] array) {
        List<String> list = new ArrayList<String>();
        for (String string : array) {
            if (StringUtils.isNotBlank(string)) {
                list.add(string);
            }
        }
        return list;
    }

    /**
     * 把数组转换成set
     *
     * @param array
     *
     * @return
     */
    public static Set<?> array2Set(Object[] array) {
        Set<Object> set = new TreeSet<Object>();
        for (Object id : array) {
            if (null != id) {
                set.add(id);
            }
        }
        return set;
    }

    /**
     * serializable toString
     *
     * @param serializable
     *
     * @return
     */
    public static String toString(Serializable serializable) {
        if (null == serializable) {
            return null;
        }
        try {
            return (String) serializable;
        } catch (Exception e) {
            return serializable.toString();
        }
    }

    /**
     * @param args
     *
     * @return
     *
     * @Title: appendString
     * @Description: 字符串追加
     * @author: aiying010
     * @return: String
     */
    public static String appendString(Object... args) {
        StringBuffer sb = new StringBuffer();
        if (args == null) {
            return "";
        }
        for (Object arg : args) {
            sb.append(String.valueOf(arg));
        }
        return sb.toString();
    }

    /**
     * @param join
     * @param args
     *
     * @return
     *
     * @Title: appendString
     * @Description: 按照指定间隔符，追加字符
     * @author: aiying010
     * @return: String
     */
    public static String appendString(String join, Object... args) {
        StringBuffer sb = new StringBuffer();
        if (args == null) {
            return "";
        }
        for (Object arg : args) {
            sb.append(String.valueOf(arg));
            sb.append(join);
        }
        return sb.toString();
    }

    /**
     * @param join
     * @param args
     *
     * @return
     *
     * @Title: appendString
     * @Description: 按照指定间隔符，追加字符
     * @author: aiying010
     * @return: String
     */
    public static String appendString(String join, String[] args) {
        return DataUtils.appendString(join, args);
    }

    /**
     * @param join
     * @param args
     *
     * @return
     *
     * @Title: appendString
     * @Description: 按照指定间隔符，追加字符
     * @author: aiying010
     * @return: String
     */
    public static String appendString(String join, List<Object> args) {
        return DataUtils.appendString(join, args);
    }

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    public static final int DIRECT_LEFT = 0;
    public static final int DIRECT_RIGHT = 1;

    /**
     * 如果参数字符串为NULL，返回""，否则返回参数本身
     *
     * @param sIn
     *
     * @return
     */
    public static String isNull(String sIn) {
        return (sIn == null) ? "" : sIn;
    }

    /**
     * 判断字符串有效性
     */
    public static boolean isValid(String src) {
        if (src == null || "".equals(src.trim())) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否为null或者为空字符串
     *
     * @param argCharSeq
     *
     * @return 是：true 否：false
     */
    public static boolean isNullOrEmpty(CharSequence argCharSeq) {
        if ((argCharSeq == null) || (argCharSeq.toString().trim().length() < 1)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为null或者不为空字符串
     *
     * @param argCharSeq
     *
     * @return 是：true 否：false
     */
    public static boolean isNotNullOrEmpty(CharSequence argCharSeq) {
        return !isNullOrEmpty(argCharSeq);
    }

    /**
     * 判断日期是否为null或者为空
     *
     * @param timestamp
     *
     * @return
     */
    public static boolean isNullOrEmpty(Timestamp timestamp) {
        if ((timestamp == null) || (timestamp.toString().trim().length() < 1)) {
            return true;
        }
        return false;
    }

    /**
     * 判断两字符串是不是相同
     *
     * @param argStr1
     * @param argStr2
     *
     * @return
     */
    public static boolean equalsString(String argStr1, String argStr2) {

        if ((argStr1 == null) && (argStr2 == null)) {
            return true;
        }
        if ((argStr1 == null) || (argStr2 == null)) {
            return false;
        }
        return argStr1.equals(argStr2);
    }

    /**
     * 把字符串的首字母转换成小写
     *
     * @param argString
     *
     * @return
     */
    public static String getFirstLowerCase(String argString) {
        if (isNullOrEmpty(argString)) {
            return "";
        }
        if (argString.length() < 2) {
            return argString.toLowerCase();
        }
        char ch = argString.charAt(0);
        ch = Character.toLowerCase(ch);
        return ch + argString.substring(1);
    }

    /**
     * 把字符串的第一个字母转换成大写
     *
     * @param argString
     *
     * @return
     */
    public static String getFirstUpperCase(String argString) {
        if (isNullOrEmpty(argString)) {
            return "";
        }
        if (argString.length() < 2) {
            return argString.toUpperCase();
        }
        char ch = argString.charAt(0);
        ch = Character.toUpperCase(ch);
        return ch + argString.substring(1);
    }

    /**
     * 换行
     *
     * @param argSbf
     */
    public static void appendLine(StringBuffer argSbf) {
        argSbf.append(System.getProperty("line.separator"));
    }

    /**
     * 格式化字符串成参数指定的格式
     *
     * @param src
     * @param argParams
     *
     * @return
     */
    public static String formatMsg(String src, Object... argParams) {
        return String.format(src, argParams);
    }

    /**
     * 得到字符串的长度
     *
     * @param src
     * @param strChar
     *
     * @return
     */
    public static int getCount(String src, String strChar) {
        int result = 0;
        int beginIndex = 0;
        int endIndex = src.indexOf(strChar, beginIndex);
        while (endIndex >= 0) {
            result++;
            beginIndex = endIndex + strChar.length();
            endIndex = src.indexOf(strChar, beginIndex);
        }
        return result;
    }

    /**
     * 替换字符串
     *
     * @param src
     * @param sFnd
     * @param sRep
     *
     * @return
     */
    public static String replaceStr(String src, String sFnd, String sRep) {
        if (src == null || "".equals(isNull(sFnd))) {
            return src;
        }

        String sTemp = "";
        int endIndex = 0;
        int beginIndex = 0;
        do {
            endIndex = src.indexOf(sFnd, beginIndex);
            if (endIndex >= 0) { // mean found it.
                sTemp = sTemp + src.substring(beginIndex, endIndex)
                        + isNull(sRep);
                beginIndex = endIndex + sFnd.length();
            } else if (beginIndex >= 0) {
                sTemp = sTemp + src.substring(beginIndex);
                break;
            }
        } while (endIndex >= 0);

        return sTemp;
    }

    /**
     * 比较两个字符串
     *
     * @param argStr1
     * @param argStr2
     *
     * @return
     */
    public static int compare(String argStr1, String argStr2) {
        if (argStr1 == null && argStr2 == null) {
            return 0;
        }
        if (argStr1 == null) {
            return -1;
        }
        if (argStr2 == null) {
            return 1;
        }
        return argStr1.compareTo(argStr2);
    }

    /**
     * @param strIn
     * @param len
     * @param direct
     *
     * @return
     */
    public static String paddingSpaceForChar(String strIn, int len, int direct) {
        if (strIn == null) {
            return null;
        }
        int strInLen = getStrLength(strIn);
        if (strInLen == len) {
            return strIn;
        } else if (strInLen > len) {
            return chopStr(strIn, len);
        } else {
            StringBuffer sb = new StringBuffer((strIn == null ? "" : strIn));
            for (int i = 0; i < (len - strInLen); i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public static boolean isHalf(char c) {
        if (!(('\uFF61' <= c) && (c <= '\uFF9F'))
                && !(('\u0020' <= c) && (c <= '\u007E'))) {
            return false;
        } else
            return true;
    }

    /**
     * 返回字符串长度
     *
     * @param s
     *
     * @return
     */
    public static int getStrLength(String s) {
        if (s == null) {
            return 0;
        }
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isHalf(s.charAt(i))) {
                len += 1;
            } else {
                len += 2;
            }
        }
        return len;
    }

    /**
     * @param s
     * @param byteLen
     *
     * @return
     */
    public static String chopStr(String s, int byteLen) {
        if (byteLen < 0) {
            return "";
        }
        if (s == null) {
            return null;
        }
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isHalf(s.charAt(i))) {
                len += 1;
            } else {
                len += 2;
            }
            if (len > byteLen) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    /**
     * 字符串转换为16进制
     *
     * @param b
     *
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 转化字节数组为16进制的字符串
     *
     * @param b 字节数组
     *
     * @return 16进制
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * MD5加密
     *
     * @param origin
     *
     * @return
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString
                    .getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 产生随机数
     *
     * @param i 随机数字的位数
     *
     * @return
     */
    public static StringBuffer generateRandomNum(int i) {
        Random random = new Random();
        StringBuffer num = new StringBuffer();
        for (int j = 0; j < i; j++) {
            int numChild = random.nextInt(10);
            num.append(numChild);

        }
        return num;
    }

    /**
     * @param i 生成随机数字的位数
     *
     * @Title: generateRandom
     * @Description: 生成随机数
     * @author: aiying010
     * @return: String 返回生成的随机数
     */
    public static String generateRandom(int i) {
        return DataUtils.generateRandomNum(i).toString();
    }

    /**
     * @return
     *
     * @Title: randomID
     * @Description: 生成UUID
     * @author: aiying015
     * @return: String
     */
    public static String randomID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成相应length长度的字符串，不够再前方补0
     *
     * @param number
     * @param length
     *
     * @return
     */
    public static String addZero(String number, int length) {

        // number是第几个包（1,2,3,4），length是流水号的位数，这里是后半部分的4位长度，length为4
        while (number.length() <= length)
            number = "0" + number;
        return number;
    }

    /**
     * 获取随机的数值。
     *
     * @param length 长度
     *
     * @return
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        boolean[] bool = new boolean[n];
        int randInt = 0;
        for (int i = 0; i < length; i++) {
            do {
                randInt = rand.nextInt(n);

            } while (bool[randInt]);

            bool[randInt] = true;
            result += randInt;
        }
        return result;
    }

    /**
     * MD5 加密
     *
     * @param str
     *
     * @return
     *
     * @throws Exception
     */
    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            LoggerUtils.fmtError(DataUtils.class, e, "MD5转换异常！message：%s",
                    e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * 判断参数是否为空 为空返回true
     *
     * @param obj
     *
     * @return
     */
    public static boolean isNull(Object obj) {
        if (obj == null || obj.toString().trim().length() == 0
                || obj.toString().toUpperCase().equals("NULL")) {
            return true;
        }
        return false;
    }

    /**
     * 判断参数是否为空（增强版）
     * <p>
     * 为空返回true
     *
     * @param obj obj中的参数假如有一个为空，则就返回true，全不为空时，才返回false
     *
     * @return
     */
    public static boolean isNull(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null || obj[i].toString().trim().length() == 0
                    || obj[i].toString().toUpperCase().equals("NULL")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断参数是否不为空
     *
     * @param obj
     *
     * @return
     */
    public static boolean isNotNull(Object obj) {
        if (obj == null || obj.toString().trim().length() == 0
                || obj.toString().toUpperCase().equals("NULL")) {
            return false;
        }
        return true;
    }

    /**
     * 判断参数是否不为空（增强版）
     *
     * @param obj 参数为obj数组，当
     *
     * @return
     */
    public static boolean isNotNull(Object... obj) {
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] == null || obj[i].toString().trim().length() == 0
                    || obj[i].toString().toUpperCase().equals("NULL")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 功能:将str转换为int
     *
     * @param str
     *
     * @return
     */
    public static int I$(String str) {
        Integer i = 0;
        try {
            if ("".equals(str)) {
                return i++;
            }
            i = Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 功能:将str转换为long
     *
     * @param str
     *
     * @return
     */

    public static Long L$(String str) {
        Long l = 0L;
        try {
            l = Long.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 功能:将str转换为float
     *
     * @param str
     *
     * @return
     */

    public static float f$(String str) {
        float aa = 0.0f;
        try {
            aa = Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aa;
    }

	/*
     * public static void main(String[] args) { String ss="5.5"; float s=
	 * DataUtils.f$(ss); s--; System.out.println(s>4.2); }
	 */

    /**
     * 功能:将str转换为list
     *
     * @param str
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List StrList(List str, String type) {
        List<String> list = new ArrayList<String>();

        try {
            if (isNull(str)) {

                return list;
            } else {
                for (int i = 0; i < str.size(); i++) {
                    Map map = (Map) str.get(i);

                    String strs = (String) map.get(type);
                    if (isBlank(strs)) {
                        list.add("00");
                    } else {

                        String[] ids = strs.split(",");
                        list = java.util.Arrays.asList(ids);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 功能:去除map中键为空或值为空的键值对
     *
     * @param map
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map dealMap(Map map) {
        if (map != null) {
            Object[] arr = map.keySet().toArray();
            System.out.println("arr.length:" + arr.length);
            System.out.println("map.keySet().size():" + map.keySet().size());
            for (int i = 0; i < arr.length; i++) {
                if (isNull(arr[i]) || isNull(map.get(arr[i]))) {
                    map.remove(arr[i]);
                }
            }
        }
        return map;
    }

    /**
     * 功能:检查map中指定key是否全不为空
     *
     * @param keys
     * @param map
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean checkMap(String keys, Map map) {
        if (isNotNull(keys) && isNotNull(map)) {
            String[] arr = keys.split(",");
            for (int i = 0; i < arr.length; i++) {
                if (isNull(map.get(arr[i]))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能:检查list中是否存在key:value
     *
     * @param key
     * @param value
     * @param list
     *
     * @return
     */
    public static boolean checkMapInList(String key, Object value,
                                         List<Map<String, Object>> list) {
        boolean ck = false;
        Map<String, Object> map = null;
        for (int i = 0; list != null && i < list.size(); i++) {
            map = list.get(i);
            if (map.get(key).equals(value)) {
                ck = true;
                break;
            }
        }
        return ck;
    }

    /**
     * 功能:根据父id获取子列表
     *
     * @param parentKey
     * @param parentValue
     * @param list
     *
     * @return
     */
    public static List<Map<String, Object>> getChildList(String parentKey,
                                                         Object parentValue, List<Map<String, Object>> list) {
        Map<String, Object> map = null;
        List<Map<String, Object>> childList = new ArrayList<Map<String, Object>>();
        if (parentKey == null || parentValue == null || list == null) {
            return childList;
        }
        for (int i = 0; list != null && i < list.size(); i++) {
            map = list.get(i);
            if (isNotNull(map.get(parentKey))
                    && parentValue.equals(map.get(parentKey))) {
                childList.add(map);
            }
        }
        return childList;
    }

    /**
     * 功能：格式化分级列表
     *
     * @param PARENT_ID
     * @param ID
     * @param list
     *
     * @return
     */
    public static List<Map<String, Object>> formatClassifyList(
            String PARENT_ID, String ID, String childName,
            List<Map<String, Object>> list) {
        if (PARENT_ID == null || ID == null || childName == null
                || list == null) {
            return null;
        }
        Map<String, Object> tmp = null;
        Map<String, Object> tmpa = null;
        List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
        // 取出一级目录
        for (int i = 0; i < list.size(); i++) {
            tmp = list.get(i);
            if (DataUtils.isNull(tmp.get(PARENT_ID))) {
                List<Map<String, Object>> childList = DataUtils.getChildList(
                        PARENT_ID, tmp.get(ID), list);
                for (int j = 0; j < childList.size(); j++) {
                    tmpa = childList.get(j);
                    tmpa.put(childName, DataUtils.getChildList(PARENT_ID,
                            tmpa.get(ID), list));
                    childList.set(j, tmpa);
                }
                tmp.put(childName, childList);
                rtnList.add(tmp);
            }
        }
        return rtnList;
    }

    /**
     * 数组转换成字符串
     *
     * @param arr 要格式化的数组
     * @param reg 间隔字符
     *
     * @return
     */
    public static String ArrayToString(String[] arr, String reg) {
        StringBuffer sb = new StringBuffer();
        if (arr == null || arr.length == 0) {
            return "";
        }
        if (arr.length == 1) {
            sb.append(arr[0] + reg);
        }
        for (String str : arr) {
            sb.append(str + reg);
        }
        if (sb.toString().endsWith(",")) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    public static String ArrayToString(Object[] arr, String reg) {
        StringBuffer sb = new StringBuffer();
        if (arr == null || arr.length == 0) {
            return "";
        }
        if (arr.length == 1) {
            sb.append(arr[0] + reg);
        }
        for (Object str : arr) {
            sb.append(str + reg);
        }
        if (sb.toString().endsWith(",")) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(DataUtils.randomUUID());
        System.out.println(DataUtils.randomUUID());
        System.out.println(DataUtils.randomUUID());
        System.out.println(DataUtils.randomUUID());
        System.out.println(DataUtils.randomUUID());
    }

	/*
     * public static void main(String[] args) { List<Map<String,Object>> list
	 * =new ArrayList<Map<String,Object>>(); Map<String, Object> map1=new
	 * HashMap<String, Object>(); map1.put("ID", "1"); map1.put("PARENT_ID",
	 * ""); Map<String, Object> map2=new HashMap<String, Object>();
	 * map2.put("ID", "2"); map2.put("PARENT_ID", "1"); Map<String, Object>
	 * map3=new HashMap<String, Object>(); map3.put("ID", "3");
	 * map3.put("PARENT_ID", "2"); list.add(map1); list.add(map2);
	 * list.add(map3); List<Map<String,Object>>
	 * rtnList=formatClassifyList("PARENT_ID", "ID", "list", list);
	 * System.out.println(Arrays.toString(rtnList.toArray())); }
	 */

    /**
     * 合并两个list
     *
     * @return
     */
    public static List<String> MergrTwoList(List<String> list1,
                                            List<String> list2) {
        List<String> listFinal = new ArrayList<String>(list1);
        listFinal.addAll(list2);
        for (int i = 0; i < listFinal.size() - 1; i++) {
            for (int j = listFinal.size() - 1; j > i; j--) {
                if (listFinal.get(j).equals(listFinal.get(i))) {
                    listFinal.remove(j);
                }
            }
        }
        return listFinal;
    }

    public static List<String> strToList(String str, String regex) {
        List<String> list = new ArrayList<String>();
        if (str.length() == 0 && "".equals(str)) {
            return list;
        } else if (str.length() == 1) {
            list.add(str);
            return list;
        }
        String temp[] = str.split(regex);
        for (String temp_ : temp) {
            list.add(temp_);
        }
        return list;
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * @param map
     *
     * @return
     *
     * @Title: mapSwitch
     * @Description: map中的null值转换为空字符串
     * @author: aiying010
     * @return: HashMap<String,Object>
     */
    public static Map<String, Object> mapSwitch(Map<String, Object> map) {
        for (String key : map.keySet()) {
            if (map.get(key) == null) {
                map.put(key, "");
            }
            Object value = map.get(key);
            if (value instanceof Date) {
                map.put(key, DateUtils.dateyyyyMMddHHmmss((Date) value));
            }
        }
        return map;
    }

    /**
     * @param str    要处理的字符串
     * @param index  字符串的开始位置
     * @param length 要处理的长度
     *
     * @return
     *
     * @Title: subString
     * @Description: 按照指定字符填充字符串 例如： 原始：123456789
     * 调用：subString("123456789",5,2,'*'); 返回：12**56789
     * @author: aiying010
     * @return: String 返回处理过的字符串
     */
    public static String subString(String str, int index, int length, char ar) {
        char[] arr = str.toCharArray();
        int len = arr.length;
        char[] out = new char[len];
        if (length > len) {
            throw new RuntimeException("指定长度超过字符串长度");
        }
        int temp = 0;
        for (int i = 0; i < len; i++) {
            if (i >= index && temp < length /* && len < length */) {
                out[i] = ar;
                temp++;
                continue;
            } else {
                out[i] = arr[i];
                continue;
            }
        }
        return new String(out);
    }

    /**
     * @param str
     *
     * @return
     *
     * @Title: subDateString
     * @Description: 将dateyyyyMMddHHmmss 转化成yyyy-MM-dd HH:mm:ss
     * @author: aiying011
     * @return: String
     */
    public static String subDateString(String str) {
        StringBuilder marStr = new StringBuilder(str);
        marStr.insert(12, ":");
        marStr.insert(10, ":");
        marStr.insert(8, " ");
        marStr.insert(6, "-");
        marStr.insert(4, "-");
        return marStr.toString();
    }

    /**
     * @param symbol
     * @param str
     *
     * @return
     *
     * @Title: cutSymbol
     * @Description: 将字符串根据特殊字符截取
     * @author: aiying011
     * @return: String[]
     */
    public static String[] cutSymbol(String symbol, String str) {
        if (DataUtils.isNotBlank(str)) {
            return str.split(symbol);
        }
        return null;
    }

    /**
     * 将图片base64字符串转换为图片字节
     * @param string
     * @return
     * @throws IOException
     */
    public static byte[] getImageByBase64Str(String string) throws IOException {
        if (string == null) {
            return new byte[]{};
        }
        byte[] images = new BASE64Decoder().decodeBuffer(string);
        return images;
    }

    public static String getImageBase64Str(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        int available = fis.available();
        byte[] buff = new byte[available];
        fis.read(buff);
        fis.close();
        String encode = new BASE64Encoder().encode(buff);
        return encode;
    }
}
