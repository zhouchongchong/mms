package cn.d9ing.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.Dynasty;
import cn.d9ing.service.IDynastyService;

@Controller
@RequestMapping("/dynasty")
public class DynastyController {
	@Resource
	private IDynastyService dynastyService;
	
	
	/**  
	 * getDynastys:获取未删除的朝代. <br/>    
	 * @author zhouchong  
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/getdynasties")
	@ResponseBody
	public List<Dynasty> getDynastys(HttpServletRequest request, Model model){
		List<Dynasty> dynasties = dynastyService.searchAllDynasty();
		return  dynasties;
	}
}
