package cn.d9ing.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class Configuration {
    private static Properties props = new Properties();

    static {
        List<String> list = new ArrayList<String>();
        list.add("system-config-resources/system-config.properties");
        Configuration.load(list);
    }

    /**
     * Get values from properties file.
     *
     * @param key the key
     *
     * @return the value
     */
    public static String getValue(String key) {
        return props.getProperty(key);
    }

    /**
     * @param names
     *
     * @Title: load
     * @Description: 加载所有配置文件
     * @author: aiying010
     * @return: void
     */
    private static void load(List<String> names) {
        for (String name : names) {
            Properties p = new Properties();
            Configuration.load(p, name);
            props.putAll(p);
        }
    }

    /**
     * @param p
     * @param name
     *
     * @Title: load
     * @Description: 加载单个配置文件
     * @author: aiying010
     * @return: void
     */
    private static void load(Properties p, String name) {
        ClassLoader loader = Configuration.class.getClassLoader();
        InputStream in = null;
        try {
            in = loader.getResourceAsStream(name);
            p.load(in);
        } catch (IOException e) {

        }
    }

    /**
     * @Title:getIntValue
     * @Description: // TODO 获取int类型的值
     * @author: aiying010
     * @return: a
     **/
    public static Integer getIntValue(String value) {
        String temp = Configuration.getValue(value);

        if (!RegularUtils.isDigital(temp)) {
            return null;

        }
        return Integer.parseInt(temp);
    }

}
