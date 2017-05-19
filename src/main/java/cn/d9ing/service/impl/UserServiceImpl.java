package cn.d9ing.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.UserMapper;
import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;
import cn.d9ing.utils.DateUtils;
import cn.d9ing.utils.JsonResult;
import cn.d9ing.utils.Keys;
import cn.d9ing.utils.MD5Encoder;
import cn.d9ing.utils.StringUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017�?5�?12�? 下午5:26:44
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userDao;

	@Override
	public JsonResult<Object> insertUser(User user) {
		boolean success = true;
		String statusCode = Keys.CODE_NORMAL;
		String data = "";
		String message = "";
		Integer resultNum = 0;
		  
		String password = user.getuPassword();
		String userName =  user.getuUsername();
		if(StringUtils.isNotBlank(userName)){
			resultNum = userDao.getSameName(userName);
			if (StringUtils.isNotBlank(resultNum)) {
				if (resultNum >=1) {
					message = "用户已存在";
				}else{
					success = true;
					message = "用户名可用";
					try {
						Map<String, String> pwdMap = MD5Encoder.getEncryptedPwd(password);
						user.setuSalt(pwdMap.keySet().iterator().next());
						user.setuPassword(pwdMap.values().iterator().next());
						user.setuRule(2);
						user.setuCreatetime(DateUtils.sqlDate());
						user.setIsdelete(0);
						Integer result = userDao.insert(user);
						data = result+"";
						message ="用户注册成功";
					} catch (Exception e) {
						 success = false;
						 statusCode = Keys.CODE_ERR;
						e.printStackTrace();  
					} 
				}
			}else{
				message = "查询出错";
			}
		}
		
		return new JsonResult<Object>(data, success, statusCode, message);
	}

	@Override
	public JsonResult<Object> validatepwd(User user) {
		boolean success = false;
		String statusCode = Keys.CODE_NORMAL;
		String data = "";
		String message = "";
		try {
			String userName = user.getuUsername();
			if (StringUtils.isNotBlank(userName)) {
				String pwd = userDao.getpwd(userName);
				if (StringUtils.isNotBlank(pwd)) {
					success = MD5Encoder.validPassword(user.getuPassword(), pwd);
					if (success) {
						message = "口令正确";
					}else{
						message = "口令错误";
					}
				}else{
					message = "查无此人";
				}
			}
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "程序报错";
			e.printStackTrace();  
		}
		return new JsonResult<Object>(data, success, statusCode, message);
	}
	@Override
	public JsonResult<Object> existSameUser(User user) {
		boolean success = false;
		String statusCode = Keys.CODE_NORMAL;
		String message = "";
		Integer resultNum = 0;
		try {
			String userName = user.getuUsername();
			if (StringUtils.isNotBlank(userName)) {
				resultNum = userDao.getSameName(userName);
				if (StringUtils.isNotBlank(resultNum)) {
					if (resultNum >=1) {
						message = "用户已存在";
					}else{
						success = true;
						message = "用户名可用";
					}
				}else{
					message = "查询出错";
				}
			}
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "程序报错";
			e.printStackTrace();  
		}
		return new JsonResult<Object>(resultNum, success, statusCode, message);
	}
	
}
