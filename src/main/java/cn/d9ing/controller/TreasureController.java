package cn.d9ing.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.d9ing.domain.Treasure;
import cn.d9ing.service.ITreasureService;
import cn.d9ing.utils.DateUtils;
import cn.d9ing.utils.Keys;
import cn.d9ing.utils.UploadUtils;
import cn.d9ing.utils.beans.FileBean;

@Controller
@RequestMapping("/treasure")
public class TreasureController {
	@Resource
	private ITreasureService treasureService;
	
	
	
	/**  
	 * getTreasurePic:钘忓搧灏侀潰鍥句笂浼?. <br/>    
	 * @author zhouchong  
	 * @return  
	 * @since JDK 1.8  
	 */
	@RequestMapping(value="/uptreasurpic",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> getTreasurePic(@RequestParam CommonsMultipartFile file, HttpServletRequest request ){
		@SuppressWarnings("unused")
		Map<String, String> pathMap = new HashMap<>();
		FileBean picfile = UploadUtils.getInstance().upload(request, file, "");
		pathMap.put("path", picfile.getRemoteUrl());
		return pathMap;
	}
	
	/**  
	 * addTreasure:新增 藏品. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param treasure
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/addtreasure")
	 @ResponseBody
	public Integer insertTreasure(HttpServletRequest request,Treasure treasure){
		try {
			Date date = DateUtils.sqlDate();
			treasure.settCreatetime(date);
			treasure.settUptime(date);
			treasure.setIsdelte(Keys.IS_NOT_DELETE);
			Integer result =  treasureService.insertTreasureSelective(treasure);
			return result;
		} catch (Exception e) {
			e.printStackTrace(); 
			return 0;
		}
	}
	 
	/**  
	 * getCurrtPageTreasure:页查询. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @return  
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/pagetreasure")
	 @ResponseBody
	public List<Treasure> getCurrtPageTreasure(HttpServletRequest request,Integer page,Integer pageSize,Integer dynasty){
		if (page <= 0 && pageSize <=0 ){
			return null;
		}
		return treasureService.searchTreasurePage(page, pageSize, dynasty);
	}
	
	/**  
	 * getTresureById:详情页. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param tId
	 * @return  
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/gettreasurebyid")
	public String getTresureById(HttpServletRequest request, Long tId,Model model){
		if (tId <= 0) {
			return null;
		}
		Treasure treasure = treasureService.selectTresureById(tId);
		model.addAttribute("treasure",treasure);
		Treasure upTreasure = treasureService.getTreasureNumUp(tId, treasure.gettDynasty());
		Treasure downTreasure = treasureService.getTreasureNumDown(tId, treasure.gettDynasty());
		model.addAttribute("upTreasure",upTreasure);
		model.addAttribute("downTreasure",downTreasure);
		return "";
	}
}
