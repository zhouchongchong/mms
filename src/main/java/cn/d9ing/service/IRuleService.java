package cn.d9ing.service;

import java.util.List;

import cn.d9ing.domain.Rule;

public interface IRuleService {
	
	public List<Rule> getRules();
	
	Rule getRule(Integer id);
}
