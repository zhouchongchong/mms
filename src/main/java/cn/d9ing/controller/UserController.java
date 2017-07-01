package cn.d9ing.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;
import cn.d9ing.utils.DataUtils;

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
		
		return userService.validatepwd(request,user);
	}
	@RequestMapping("/isin")
	@ResponseBody
	public Object isIn(HttpServletRequest request,String userName){
		Map<String, Object> resultMap = new HashMap<>();
		Integer num = 0;
		User user = (User)request.getSession().getAttribute("user");
		request.getSession().setMaxInactiveInterval(10800);
		if(!DataUtils.isBlank(user)){
			if(user.getuUsername().equals(userName)){
				num = 1;
			}
		}
		resultMap.put("result", num);
		return resultMap;
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
	
	/** 
	* @Title: getUserPage 
	* @Description: user分页
	* @param @param request
	* @param @param page
	* @param @param rows
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@RequestMapping("userpage")
	@ResponseBody
	public Object getUserPage(HttpServletRequest request,Integer page,Integer rows){
		return userService.searchPageUser(page, rows);
	}
	
	/** 
	* @Title: getUserById 
	* @Description: 根据id获得用户详细信息
	* @param @param request
	* @param @param uId
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@RequestMapping("/userbyid")
	@ResponseBody
	public Object getUserById(HttpServletRequest request,Integer uId){
		
		return userService.searchUserById(uId);
	}
	/** 
	* @Title: deleteUserById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @param uId
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@RequestMapping("/deleteuser")
	@ResponseBody
	public Object deleteUserById(HttpServletRequest request,Integer uId){
		return userService.deleteUserById(uId);
	}
}
