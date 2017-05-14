package cn.d9ing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.d9ing.dao.RuleMapper;
import cn.d9ing.domain.Rule;
import cn.d9ing.service.IRuleService;
@Service
public class RuleServiceImpl implements IRuleService {
	@Autowired
	private RuleMapper ruleDao;
	public List<Rule> getRules() {
		  
		// TODO Auto-generated method stub  
		return ruleDao.getRules();
	}

	public Rule getRule(Integer id) {
		  
		// TODO Auto-generated method stub  
		return ruleDao.selectByPrimaryKey(id);
	}
}
