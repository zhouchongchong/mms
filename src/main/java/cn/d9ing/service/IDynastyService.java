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

import cn.d9ing.domain.Dynasty;

public interface IDynastyService {
	
	/**  
	 * searchAllDynasty:返回�?有未删除的朝�?. <br/>    
	 * @author zhouchong  
	 * @return  list
	 * @since JDK 1.8  
	 */
	public List<Dynasty> searchAllDynasty();
	
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
  
