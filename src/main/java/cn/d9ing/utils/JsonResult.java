package cn.d9ing.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonResult<D> implements Serializable {

	/**
	 * @Title: JsonResult.java
	 * @Package cn.d9ing.utils
	 * @Description: 统一返回状态类
	 * @author A18ccms A18ccms_gmail_com
	 * @date 2017年5月17日 下午4:30:56
	 * @version V1.0
	 */
	private static final long serialVersionUID = 7279730538823880333L;

	private D data; // 返回数据源
	private boolean success; // 系统报错返回false 成功返回true
	private String statusCode; // 返回状态码
	private String message; // 返回备注

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	public JsonResult() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JsonResult(D data, boolean success, String statusCode, String message) {
		super();
		if (data instanceof Map) {
			data = (D) DataUtils.mapSwitch((Map) data);
		} else if (data instanceof List) {
			List list = (List) data;
			if (!list.isEmpty()) {
				data = (D) DataUtils.listSwitch(list);
			} else {
				data = (D) "";
			}
		}
		this.data = data;
		this.success = success;
		this.statusCode = statusCode;
		this.message = message;
	}

	public D getData() {
		return data;
	}

	@SuppressWarnings("unchecked")
	public void setData(D data) {
		if (data instanceof Map) {
			data = (D) DataUtils.mapSwitch((Map) data);
		}
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@SuppressWarnings("unused")
	public String jsonString(){
		if (this == null) {
			return "";
		}

		return JSON.toJSONString(this);
	}

}
