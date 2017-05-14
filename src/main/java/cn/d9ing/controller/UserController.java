package cn.d9ing.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.d9ing.service.IUserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Resource
	private IUserService userService;
	
}
