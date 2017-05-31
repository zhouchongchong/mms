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
import cn.d9ing.utils.JsonResult;
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
		pathMap.put("realpath", picfile.getLocalUrl());
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
//			FileBean picfile = UploadUtils.getInstance().upload(request, file, "");
			treasure.settCreatetime(date);
			treasure.settUptime(date);
//			treasure.settCoverUrl(picfile.getRemoteUrl());
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
	public Map<String, Object> getCurrtPageTreasure(HttpServletRequest request,Integer page,Integer rows,Integer dynasty){
		 Map<String, Object> resultMap = new HashMap<>();
		 Integer records = 0;
		 Integer totalpage = 0;
		if (page <= 0 && rows <=0 ){
			return null;
		}
		//获取藏品
		List<Treasure> treasures = treasureService.searchTreasurePage(page, rows, dynasty);
		if(treasures!=null &&!treasures.isEmpty()){
			 records = treasures.size();
		}
		Integer  totalnum =  treasureService.getPageNum(rows,dynasty);
		if(totalnum%rows > 0){
			totalpage = (totalnum/rows) + 1;
		}else {
			totalpage = totalnum/rows;
		}
		resultMap.put("page", page);
		resultMap.put("records", totalnum);
		resultMap.put("rows", treasures);
		resultMap.put("total", totalpage);
		return resultMap;
	}
	 /**  
		 * getPage:分页总页数. <br/>    
		 * @author zhouchong  
		 * @param pageSize
		 * @return  
		 * @since JDK 1.8  
		 */
		 @RequestMapping("gettreasurepage") 
		 @ResponseBody
		public Integer getPage(Integer pageSize,Integer dynasty){
			Integer  page =  treasureService.getPageNum(pageSize,dynasty);
			return page;
		}
	/**  
	 * updateTreasureById:根据ID修改treasure. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param treasure
	 * @return  
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/updatetreasure")
	 @ResponseBody
	public Integer updateTreasureById(HttpServletRequest request,Treasure treasure){
		Integer result = 0;
		try {
			result = treasureService.updateByPrimaryKeySelective(treasure);
		} catch (Exception e) {
			  
			return result;
			
		}
		return result;
	}
	 /**  
	 * deleteTreasureById:删除treasure. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param treasure
	 * @return  
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/deletetreasure")
	 @ResponseBody
	public Integer deleteTreasureById(HttpServletRequest request,Treasure treasure){
		 Integer result = 0;
		 try {
			 treasure.setIsdelte(Keys.IS_DELETE);
			 result = treasureService.updateByPrimaryKeySelective(treasure);
		 } catch (Exception e) {
			 
			 return result;
			 
		 }
		 return result;
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
	 @ResponseBody
	public Map<String, Treasure> getTresureById(HttpServletRequest request, Long tId,Model model){
		 Map<String, Treasure> treasures = new HashMap<>();
		if (tId <= 0) {
			return null;
		}
		Treasure treasure = treasureService.selectTresureById(tId);
		Treasure upTreasure = treasureService.getTreasureNumUp(tId, treasure.gettDynasty());
		Treasure downTreasure = treasureService.getTreasureNumDown(tId, treasure.gettDynasty());
		treasures.put("treasure", treasure);
		treasures.put("upTreasure", upTreasure);
		treasures.put("downTreasure", downTreasure);
		return treasures;
	}
	 /**  
	 * getBackTresureById:后台 藏品详情. <br/>    
	 * @author zhouchong  
	 * @param request
	 * @param tId
	 * @param model
	 * @return  
	 * @since JDK 1.8  
	 */
	 @RequestMapping("/getbacktreasurebyid")
	 @ResponseBody
	public Treasure getBackTresureById(HttpServletRequest request, Long tId,Model model){
			if (tId <= 0) {
				return null;
			}
			Treasure treasure = treasureService.selectTresureById(tId);
			return treasure;
		}
	
	 
	/** 
	* @Title: getNextTreasureNum 
	* @Description: 下一个number 
	* @param @param dynasty
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* @throws 
	*/
	 @RequestMapping("/getnexttreasurenum")
	 @ResponseBody
	public Map<String, String> getNextTreasureNum(Integer dynasty){
		Map<String, String> map = new HashMap<>();
		String number = treasureService.getNextTreasureNum(dynasty);
		map.put("treasurenum", number);
		return map;
	}
	@RequestMapping("/indextreasure")
	@ResponseBody
	public Object getIndexTreasure(){
		return treasureService.getIndexTreasur();
		
	}
}
