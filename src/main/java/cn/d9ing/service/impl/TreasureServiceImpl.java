package cn.d9ing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.d9ing.dao.TreasureMapper;
import cn.d9ing.domain.Treasure;
import cn.d9ing.service.ITreasureService;
import cn.d9ing.utils.JsonResult;
import cn.d9ing.utils.Keys;
import cn.d9ing.utils.StringUtils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author zcc
 * @data 2017�?5�?12�? 下午5:30:56
 */
@Service
public class TreasureServiceImpl implements ITreasureService{
	@Autowired
	private TreasureMapper treasureDao;

	public Integer insertTreasureSelective(Treasure treasure) {
		  
		// TODO Auto-generated method stub  
		return treasureDao.insertSelective(treasure);
	}

	public List<Treasure> searchTreasurePage(Integer pageSize, Integer size,Integer dynasty) {
		  
		Integer begain  =(pageSize - 1) * size;
		Integer end =  pageSize * size;
		
		return treasureDao.selectTreasurePage(begain, end, dynasty);
	}

	public Treasure selectTresureById(Long tId) {
		  
		return treasureDao.selectByPrimaryKey(tId);
	}

	public Treasure getTreasureNumUp(Long tId, Integer dynasty) {
		Treasure treasur = treasureDao.getTreasureNumUp(tId, dynasty);
		  
		return treasur;
	}

	public Treasure getTreasureNumDown(Long tId, Integer dynasty) {
		Treasure treasur = treasureDao.getTreasureNumDown(tId, dynasty);
		  
		return treasur;
	}

	public Integer updateByPrimaryKeySelective(Treasure record) {
		  
		return treasureDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public Integer getPageNum(Integer pageSize,Integer dynasty) {
		  
		Integer total  =  treasureDao.getPageNum(dynasty);
		return total;
	}

	@Override
	public String getNextTreasureNum(Integer dynasty) {
		
		return  treasureDao.getNextTreasureNum(dynasty);
	}

	@Override
	public JsonResult<Object> getIndexTreasur() {
		String message = "";
		Map<String, List> data = new HashMap<>();
		boolean success = true;
		String statusCode = Keys.CODE_NORMAL;
		try {
			data.put("treasures", treasureDao.getIndexTreasure());
			if(StringUtils.isNotBlank(data)&&(int)data.size() >= 1){
				message = "获取成功";
			}else{
				message = "获取失败";
			}
		} catch (Exception e) {
			success = false;
			statusCode = Keys.CODE_ERR;
			message = "系统报错";
			e.printStackTrace();
		}
		System.out.println(data);
		return new JsonResult<Object>(data, success, statusCode, message);  
	}
	
}
