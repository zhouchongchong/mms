package cn.d9ing.dao;

import java.util.List;

import cn.d9ing.domain.Dynasty;

public interface DynastyMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(Dynasty record);

    int insertSelective(Dynasty record);

    Dynasty selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Dynasty record);

    int updateByPrimaryKey(Dynasty record);
    
    List<Dynasty> selectAllDynasty();
}