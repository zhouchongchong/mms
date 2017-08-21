package cn.d9ing.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;
import cn.d9ing.utils.DataUtils;
import cn.d9ing.utils.MailUtil;

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
	 * @throws MessagingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws AddressException 
	 * @since JDK 1.8  
	 */
	@RequestMapping("/adduser")
	@ResponseBody
	public Object registerUser(HttpServletRequest request,User user) throws AddressException, NoSuchAlgorithmException, MessagingException{
		User u = new User();
		u = MailUtil.activateMail(user);
		return userService.insertUser(u);
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
	@RequestMapping(value = "/activatemail")
	public Object regedisUser(HttpServletRequest request,Model model) throws AddressException, NoSuchAlgorithmException, MessagingException{
		//获取激活参数
        String email = request.getParameter("email");
        String token = request.getParameter("token");
        Integer uId = Integer.parseInt(request.getParameter("id"));
        Long time = System.currentTimeMillis();
        User u = userService.searchUser(uId);
//        UserDTO ud = new UserDTO();
//        ud.setMail(email);
        if(u!=null) {
//            ud.setEmail(1);
            if(u.getIsdelete()==1&&u.getActivateTime()!=1) {
                if(u.getActivateTime()<time) {
                    //过期--激活失败
                    u.setActivateTime(Long.parseLong("-1"));
                    //重新发送激活邮件
                    u = MailUtil.activateMail(u);
                    //重新设置了有效时间和token激活码
                    userService.updateUserSelective(u);
//                    ud.setTime(-1);
//                    model.addAttribute("user", JsonUtil.toJson(u));
                    //resp.getWriter().write(JsonUtil.toJson(u));
                } else if (u.getActivateTime()>time){
                    //在时间内
                    u.setActivateTime(Long.parseLong("1"));
//                    ud.setTime(1);
                    if(u.getToken().equals(token)) {
                        //在时间内且激活码通过，激活成功
                        u.setIsdelete(0);
                        u.setuCreatetime(new Date());;
                        //重新设置token防止被禁用的用户利用激活
                        u.setToken(token.replace("1", "c"));
                        User user = new User();
                        user.setuId(uId);
                        user.setIsdelete(0);
                        user.setToken(token.replace("1", "c"));
                        u.setuCreatetime(new Date());
                        userService.updateUserSelective(user);
//                        ud.setToken(1);
//                        ud.setStatus(1);
//                        model.addAttribute("user", JsonUtil.toJson(ud));
                        //resp.getWriter().write(JsonUtil.toJson(u));
                    } else {
                        //在时间内但是且激活码错误
//                        ud.setToken(-1);
//                        model.addAttribute("user", JsonUtil.toJson(ud));
                    }
                }
                //u.getStatus()!=1判断结束
            } else if(u.getIsdelete()==0) {
                //已经被激活的重复点链接
//                ud.setStatus(-1);
//                model.addAttribute("user", JsonUtil.toJson(ud));
            }
        //u为空
        } else if(u==null) {
//            ud.setEmail(-1);
//            model.addAttribute("user", JsonUtil.toJson(ud));
        }
        return "activatemail";
		
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
