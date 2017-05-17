package cn.d9ing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.d9ing.domain.Dynasty;

public interface DynastyMapper {
    int deleteByPrimaryKey(Integer dId);

    int insert(Dynasty record);

    int insertSelective(Dynasty record);

    Dynasty selectByPrimaryKey(Integer dId);

    int updateByPrimaryKeySelective(Dynasty record);

    int updateByPrimaryKey(Dynasty record);
    
    List<Dynasty> selectAllDynasty();
    
    List<Dynasty> getPageAliveDynasty(@Param(value = "begain")Integer begain,@Param(value = "end")Integer end);
}