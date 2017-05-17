/**  
 * Project Name:jiuwenxuan  
 * File Name:IDynastyService.java  
 * Package Name:com.jiuwenxuan.wenbo.service  
 * Date:2017�?5�?13日上�?10:55:33  
 * Copyright (c) 2017, 7640*****@qq.com All Rights Reserved.  
 *  
*/  
  
package cn.d9ing.service;  
/**  
 * ClassName:IDynastyService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017�?5�?13�? 上午10:55:33 <br/>  
 * @author   zhouchong  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */

import java.util.List;
import java.util.Map;

import cn.d9ing.domain.Dynasty;

public interface IDynastyService {
	
	/**  
	 * searchAllDynasty:返回�?有未删除的朝�?. <br/>    
	 * @author zhouchong  
	 * @return  list
	 * @since JDK 1.8  
	 */
	public Map<String, Object> searchPageAllDynasty(Integer page,Integer rows);
	/**  
	 * searchAllDynasty:(这里用一句话描述这个方法的作用). <br/>    
	 * @author zhouchong  
	 * @param page
	 * @param rows
	 * @return  
	 * @since JDK 1.8  
	 */
	List<Dynasty> searchAllDynasty();
	
	/**  
	 * insertDynasty:新增朝代. <br/>    
	 * @author zhouchong  
	 * @param dynasty
	 * @return  
	 * @since JDK 1.8  
	 */
	public int insertDynasty(Dynasty dynasty);
	
	/**  
	 * updateByPrimaryKeySelective:修改朝代. <br/>    
	 * @author zhouchong  
	 * @param dynasty
	 * @return  
	 * @since JDK 1.8  
	 */
	int updateByPrimaryKeySelective(Dynasty dynasty);
}
  
