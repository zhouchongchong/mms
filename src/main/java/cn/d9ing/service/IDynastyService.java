/**  
 * Project Name:jiuwenxuan  
 * File Name:IDynastyService.java  
 * Package Name:com.jiuwenxuan.wenbo.service  
 * Date:2017å¹?5æœ?13æ—¥ä¸Šå?10:55:33  
 * Copyright (c) 2017, 7640*****@qq.com All Rights Reserved.  
 *  
*/  
  
package cn.d9ing.service;  
/**  
 * ClassName:IDynastyService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017å¹?5æœ?13æ—? ä¸Šåˆ10:55:33 <br/>  
 * @author   zhouchong  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */

import java.util.List;

import cn.d9ing.domain.Dynasty;

public interface IDynastyService {
	
	/**  
	 * searchAllDynasty:è¿”å›æ‰?æœ‰æœªåˆ é™¤çš„æœä»?. <br/>    
	 * @author zhouchong  
	 * @return  list
	 * @since JDK 1.8  
	 */
	public List<Dynasty> searchAllDynasty();
}
  
