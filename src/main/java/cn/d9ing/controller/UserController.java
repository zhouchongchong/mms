package cn.d9ing.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private IUserService userService;
	
	/**  
	 * registerUser:注册用户. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param user
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/adduser")
	@ResponseBody
	public Object registerUser(HttpServletRequest request,User user){
		return userService.insertUser(user);
	}
	
	/**  
	 * validateUser:登录验证. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param user
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/validateuser")
	@ResponseBody
	public Object validateUser(HttpServletRequest request,User user){
		
		return userService.validatepwd(user);
	}
	
	/**  
	 * exitUser:验证用户名是否重复. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param user
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/exituser")
	@ResponseBody
	public Object exitUser(HttpServletRequest request,User user){
		return userService.existSameUser(user);
	}
}
