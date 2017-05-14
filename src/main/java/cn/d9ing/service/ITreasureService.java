package cn.d9ing.service;

import java.util.List;

import cn.d9ing.domain.Treasure;

public interface ITreasureService {
	
	/**  
	 * insertTreasureSelective:新增藏品. <br/>    
	 * @author zhouchong  
	 * @param treasure
	 * @return  
	 * @since JDK 1.8  
	 */
	Integer insertTreasureSelective(Treasure treasure);
	
	/**  
	 * searchTreasurePage:分页查询 页查询. <br/>    
	 * @author zhouchong  
	 * @param pageSize
	 * @param size
	 * @return  
	 * @since JDK 1.8  
	 */
	List<Treasure> searchTreasurePage(Integer pageSize, Integer size,Integer dynasty);
	
	/**  
	 * selectTresureById:根据ID查询. <br/>    
	 * @author zhouchong  
	 * @param tId
	 * @return  
	 * @since JDK 1.8  
	 */
	Treasure selectTresureById(Long tId);
	
	Treasure getTreasureNumUp(Long tId,Integer dynasty);
	
	Treasure getTreasureNumDown(Long tId,Integer dynasty);
}
