package cn.d9ing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.TreasureMapper;
import cn.d9ing.domain.Treasure;
import cn.d9ing.service.ITreasureService;
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
		if(total%pageSize > 0){
			return (total/pageSize) + 1;
		}else {
			return total/pageSize;
		}
	}
	
}
