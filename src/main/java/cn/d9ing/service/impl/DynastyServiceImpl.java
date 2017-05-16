package cn.d9ing.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.DynastyMapper;
import cn.d9ing.domain.Dynasty;
import cn.d9ing.service.IDynastyService;
@Service
public class DynastyServiceImpl implements IDynastyService {
	@Autowired
	private DynastyMapper dynastyDao;

	public Map<String, Object> searchPageAllDynasty(Integer page,Integer rows) {
		Map<String, Object> resultMap = new HashMap<>();
		Integer totalnum =  0;
		Integer totalpage = 0; 
		Integer begain  =(page - 1) * rows;
		Integer end =  page * rows;
		List<Dynasty> total = dynastyDao.selectAllDynasty();
		List<Dynasty> dynasties = dynastyDao.getPageAliveDynasty(begain,end);
		if (dynasties != null && !dynasties.isEmpty()){
			totalnum = total.size();
		}
		if(totalnum%rows > 0){
			totalpage = (totalnum/rows) + 1;
		}else {
			totalpage = totalnum/rows;
		}
		resultMap.put("page", page);//当前页数
		resultMap.put("records", totalnum);//总数
		resultMap.put("rows", dynasties);//返回的list
		resultMap.put("total", totalpage);//总页数
		return resultMap;
	}

	public int insertDynasty(Dynasty dynasty) {
		  
		
		return dynastyDao.insert(dynasty);
	}

	public int updateByPrimaryKeySelective(Dynasty dynasty) {
		  
		return dynastyDao.updateByPrimaryKeySelective(dynasty);
	}

	@Override
	public List<Dynasty> searchAllDynasty() {
		  
		// TODO Auto-generated method stub  
		return  dynastyDao.selectAllDynasty();
	}
}
