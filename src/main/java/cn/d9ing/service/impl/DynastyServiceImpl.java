package cn.d9ing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.DynastyMapper;
import cn.d9ing.domain.Dynasty;
import cn.d9ing.service.IDynastyService;
@Service
public class DynastyServiceImpl implements IDynastyService {
	@Autowired
	private DynastyMapper dynastyDao;

	public List<Dynasty> searchAllDynasty() {
		  
		List<Dynasty> dynasties = dynastyDao.selectAllDynasty();  
		return dynasties;
	}

	public int insertDynasty(Dynasty dynasty) {
		  
		
		return dynastyDao.insert(dynasty);
	}

	public int updateByPrimaryKeySelective(Dynasty dynasty) {
		  
		return dynastyDao.updateByPrimaryKeySelective(dynasty);
	}
}
