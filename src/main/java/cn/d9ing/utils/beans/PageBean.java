package cn.d9ing.utils.beans;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
	/**   
	* @Title: PageBean.java 
	* @Package cn.d9ing.utils.beans 
	* @Description: TODO(用一句话描述该文件做什么) 
	* @author A18ccms A18ccms_gmail_com   
	* @date 2017年5月19日 下午4:12:29 
	* @version V1.0   
	*/
	private static final long serialVersionUID = -900076496073236228L;
	private Integer page = 0;
	private Integer records = 0;
	private List rows ;
	private Integer total = 0;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	

}
