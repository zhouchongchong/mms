package cn.d9ing.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.d9ing.domain.Dynasty;
import cn.d9ing.service.IDynastyService;
import cn.d9ing.utils.DateUtils;
import cn.d9ing.utils.Keys;
import cn.d9ing.utils.StringUtils;

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
	@RequestMapping(value ="/getdynasties")
	@ResponseBody
	public List<Dynasty> getDynastys(HttpServletRequest request, Model model,HttpServletResponse response){
		List<Dynasty> dynasties = dynastyService.searchAllDynasty();
		return  dynasties;
	}
	
	/**  
	 * insertDynasty:新增朝代. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/adddynasty")
	@ResponseBody
	public Integer insertDynasty(HttpServletRequest request, Dynasty dynasty){
		Integer result = 0;
		if(StringUtils.isBlank(dynasty.getdName())){
			return result;
		}else {
			dynasty.setIsdelete(Keys.IS_NOT_DELETE);
			dynasty.setdCreatetime(DateUtils.sqlDate());
			return dynastyService.insertDynasty(dynasty);
		}
	}
	
	/**  
	 * updateDynasty:修改朝代. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param dynasty
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/updatedynasty")
	@ResponseBody
	public Integer updateDynasty(HttpServletRequest request, Dynasty dynasty){
			
			return dynastyService.updateByPrimaryKeySelective(dynasty);
		}
	
	/**  
	 * deleteDynastyById:删除朝代. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param dynasty
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping("/deletedynasty")
	@ResponseBody
	public Integer deleteDynastyById(HttpServletRequest request, Dynasty dynasty){
		dynasty.setIsdelete(Keys.IS_DELETE);
		return dynastyService.updateByPrimaryKeySelective(dynasty);
	}
}
