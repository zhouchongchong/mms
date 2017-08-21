package cn.d9ing.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.UserMapper;
import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;
import cn.d9ing.utils.DateUtils;
import cn.d9ing.utils.JsonResult;
import cn.d9ing.utils.Keys;
import cn.d9ing.utils.MD5Encoder;
import cn.d9ing.utils.MailUtil;
import cn.d9ing.utils.StringUtils;
import cn.d9ing.utils.beans.PageBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017�?5�?12�? 下午5:26:44
 */
@Service
public class UserServiceImpl implements IUserService {
	boolean success = true;
	String statusCode = Keys.CODE_NORMAL;
	String message = "";
	Object data = null;
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
		String userName = user.getuUsername();
		if (StringUtils.isNotBlank(userName)) {
			resultNum = userDao.getSameName(userName);
			if (StringUtils.isNotBlank(resultNum)) {
				if (resultNum >= 1) {
					message = "用户已存在";
				} else {

					success = true;
					message = "用户名可用";
					int count = userDao.getSameEMail(user.getuEmail());
					if (count < 1) {
						try {
							user = MailUtil.activateMail(user);
							Map<String, String> pwdMap = MD5Encoder.getEncryptedPwd(password);
							user.setuSalt(pwdMap.keySet().iterator().next());
							user.setuPassword(pwdMap.values().iterator().next());
							user.setuRule(2);
							user.setuCreatetime(DateUtils.sqlDate());
							user.setIsdelete(1);
							Integer result = userDao.insertSelective(user);
							data = result + "";
							
							message = "用户注册成功";

						} catch (Exception e1) {
							success = false;
							statusCode = Keys.CODE_ERR;
							e1.printStackTrace();
						}
					} else {
						message = "邮箱已存在";
					}
				}
			} else {
				message = "查询出错";
			}
		}

		return new JsonResult<Object>(data, success, statusCode, message);
	}

	@Override
	public JsonResult<Object> validatepwd(HttpServletRequest request,User user) {
		boolean success = false;
		String statusCode = Keys.CODE_NORMAL;
		String data = "";
		String message = "";
		try {
  			String userName = user.getuUsername();
			if (StringUtils.isNotBlank(userName)) {
				String pwd = userDao.getpwd(user);
				if (StringUtils.isNotBlank(pwd)) {
					success = MD5Encoder.validPassword(user.getuPassword(), pwd);
					if (success) {
						message = "密码正确";
						request.getSession().setAttribute("user", user);
						request.getSession().setMaxInactiveInterval(10800);
					}else{
						message = "密码错误";
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

	@Override
	public PageBean searchPageUser(Integer page, Integer rows) {
	
		PageBean pageBean = new PageBean();
		Integer begain  =(page - 1) * rows;
		Integer end =  page * rows;
		try {
			List<User> users = userDao.searchPageUser(begain, end);
			pageBean.setRows(users);
			pageBean.setPage(page);
			if (StringUtils.isNotBlank(users)&&!users.isEmpty()) {
				Integer totalnum = users.get(0).getTotalnum();
				pageBean.setRecords(totalnum);
				if(totalnum%rows > 0){
					pageBean.setTotal((totalnum/rows) + 1);
				}else {
					pageBean.setTotal(totalnum/rows);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pageBean;
	}

	@Override
	public JsonResult<Object> searchUserById(Integer uId) {
		boolean success = true;
		String statusCode = Keys.CODE_NORMAL;
		String message = "";
		User data = null;
		try {
			data = userDao.selectByPrimaryKey(uId);
			message = "用户完整信息";
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "系统报错";
			e.printStackTrace();
		}
		
		return new JsonResult<Object>(data, success, statusCode, message);
	}
	
	public User searchUser(String email){
		User data = null;
		try {
			data = userDao.selectByEmail(email);
			message = "用户完整信息";
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "系统报错";
			e.printStackTrace();
		}
		return data;
		
	}

	@Override
	public JsonResult<Object> deleteUserById(Integer uId) {
		try {
			data =userDao.deleteByPrimary(uId);
			if(StringUtils.isNotBlank(data)&&(int)data == 1){
				message = "修改成功";
			}else{
				message = "修改失败";
			}
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "系统报错";
			e.printStackTrace();
		}
		return new JsonResult<Object>(data, success, statusCode, message);
	}

	@Override
	public boolean updateUserSelective(User u) {
		boolean result = false;
		int count = userDao.updateuser(u);
		if (count==1){
			result = true;
		}
		 
		return result;
	}

	@Override
	public Integer selectByEMail(String email) {
		  

		return userDao.getSameEMail(email);
	}
	
	
	
}
