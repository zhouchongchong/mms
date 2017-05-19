package cn.d9ing.dao;

import org.apache.ibatis.annotations.Param;

import cn.d9ing.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    String getpwd(@Param(value = "username")String userName);
    
    Integer getSameName(@Param(value = "username")String userName);
}