package cn.d9ing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.d9ing.domain.Treasure;

public interface TreasureMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(Treasure record);

    int insertSelective(Treasure record);

    Treasure selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(Treasure record);

    int updateByPrimaryKeyWithBLOBs(Treasure record);

    int updateByPrimaryKey(Treasure record);
    
    List<Treasure> selectTreasurePage(@Param(value = "begain")Integer begain,@Param(value = "end")Integer end,@Param(value = "dynasty") Integer dynasty);
    
    Treasure getTreasureNumUp(@Param(value = "tId")Long tId,@Param(value = "dynasty") Integer dynasty);
    
    Treasure getTreasureNumDown(@Param(value = "tId")Long tId,@Param(value = "dynasty") Integer dynasty);
    
    Integer getPageNum(@Param(value = "dynasty")Integer dynasty);
}