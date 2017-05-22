package cn.d9ing.service;

import cn.d9ing.domain.User;
import cn.d9ing.utils.JsonResult;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017�?5�?12�? 下午5:26:01
 */
public interface IUserService {
	
	JsonResult<Object> insertUser(User user);
	
	
	JsonResult<Object> validatepwd(User user);
	
	JsonResult<Object> existSameUser(User user);
	
	JsonResult<Object> searchPageUser(Integer page,Integer rows);
	
	JsonResult<Object> searchUserById(Integer uId);
	
	JsonResult<Object> deleteUserById(Integer uId);
	
	
}
