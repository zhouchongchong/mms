package cn.d9ing.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.d9ing.domain.User;
import cn.d9ing.utils.JsonResult;
import cn.d9ing.utils.beans.PageBean;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017�?5�?12�? 下午5:26:01
 */
public interface IUserService {
	
	JsonResult<Object> insertUser(User user);
	
	public User searchUser(String email);
	
	JsonResult<Object> validatepwd(HttpServletRequest request,User user);
	
	JsonResult<Object> existSameUser(User user);
	
	PageBean searchPageUser(Integer page,Integer rows);
	
	JsonResult<Object> searchUserById(Integer uId);
	
	JsonResult<Object> deleteUserById(Integer uId);
	
	boolean updateUserSelective(User u);
	
	Integer selectByEMail(String email);
	
	
	
}
