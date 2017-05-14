package cn.d9ing.dao;

import java.util.List;

import cn.d9ing.domain.Rule;

public interface RuleMapper {
    int deleteByPrimaryKey(Integer rId);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Integer rId);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);
    
    List<Rule> getRules();
    
}