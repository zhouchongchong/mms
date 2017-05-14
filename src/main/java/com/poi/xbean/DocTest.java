/**  
 * Copyright © 2017北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: DocTest.java
 * @Prject: mms
 * @Package: com.poi.xbean
 * @Description: TODO
 * @author:  aiying014
 * @date: 2017年3月14日 下午2:10:15
 * @version: V1.0  
 */
package com.poi.xbean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017年3月14日 下午2:10:15
 */
public class DocTest {

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) throws IOException {
		Map<String, Object> param = new HashMap<String, Object>();  
        param.put("${apUser}", "周冲");  
        param.put("${apDate}", "2017年6月03日");  
        param.put("${artType}", "油画");  
        param.put("${supDate}", "2017年6月23日");  
        param.put("${artname}", "风雪夜");  
        param.put("${zhuzhe}", "周冲冲");  
        param.put("${years}", "公元前2013");  
        param.put("${chicun}", "10……10");  
        param.put("${paper}", "纸质");  
        param.put("${zhuangbiao}", "精装");  
//        param.put("${zongfen}", "60");  
        Map<String,Object> header = new HashMap<String, Object>();  
        header.put("width", 300);  
        header.put("height", 250);  
        header.put("type", "png");  
        header.put("content", "F:\\Downloads\\123.png");  
        param.put("{qianzi}",header);  
        XWPFDocument doc = WordUtil.generateWord(param, "f:\\2.docx");  
        FileOutputStream fopts = new FileOutputStream("f:/3.docx");  
        doc.write(fopts);  
        fopts.close();  
		
	}

}
