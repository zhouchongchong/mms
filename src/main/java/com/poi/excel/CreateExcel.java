/**  
 * Copyright © 2017北京鼎九信息工程研究院有限公司. All rights reserved.
 *
 * @Title: CreateExcel.java
 * @Prject: mms
 * @Package: com.poi.excel
 * @Description: TODO
 * @author:  aiying014
 * @date: 2017年3月20日 下午4:44:24
 * @version: V1.0  
 */
package com.poi.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017年3月20日 下午4:44:24
 */
public class CreateExcel {

	/** 
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) {
		HSSFWorkbook wb = new HSSFWorkbook();// create webbook for excel
		HSSFSheet sheet = wb.createSheet("word模板");//create sheet 
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style  = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell =  row.createCell(1);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);
		cell =  row.createCell(2);
		cell.setCellValue("身份");
		cell.setCellStyle(style);
		cell =  row.createCell(3);
		cell.setCellValue("出生日期");
		cell.setCellStyle(style);
		cell =  row.createCell(4);
		cell.setCellValue("籍贯");
		cell.setCellStyle(style);
		cell =  row.createCell(5);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell =  row.createCell(6);
		cell.setCellValue("学历");
		cell.setCellStyle(style);
		
		for(int i = 0; i <= 300; i++){
			row = sheet.createRow(i+1);
			row.createCell(0).setCellValue("尹兆祥");  
            row.createCell(1).setCellValue(24);  
            row.createCell(2).setCellValue("IT男");  
            row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(new Date(990,4,20)));  
            row.createCell(4).setCellValue("皖");  
            row.createCell(5).setCellValue("男");  
            row.createCell(6).setCellValue("本科");  
		}
		
		try {
			FileOutputStream fout =  new FileOutputStream("F:/word模板.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				

	}

}
