package cn.d9ing.dao;

import cn.d9ing.domain.Treasure;

public interface TreasureMapper {
    int deleteByPrimaryKey(Long tId);

    int insert(Treasure record);

    int insertSelective(Treasure record);

    Treasure selectByPrimaryKey(Long tId);

    int updateByPrimaryKeySelective(Treasure record);

    int updateByPrimaryKeyWithBLOBs(Treasure record);

    int updateByPrimaryKey(Treasure record);
}