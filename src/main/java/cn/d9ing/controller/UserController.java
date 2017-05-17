package cn.d9ing.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.d9ing.domain.User;
import cn.d9ing.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private IUserService userService;
	
	public Object registerUser(HttpServletRequest request,User user){
		return null;
	}
}
