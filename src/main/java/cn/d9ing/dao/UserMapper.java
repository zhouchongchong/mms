package cn.d9ing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.d9ing.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);
    
    int updateuser(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);
    
    User selectByEmail(@Param(value = "uEmail")String uEmail);

    int updateByPrimaryKeySelective(User record);
    
    int deleteByPrimary(Integer uId);

    int updateByPrimaryKey(User record);
    
    String getpwd(User user);
    
    Integer getSameName(@Param(value = "username")String userName);
    Integer getSameEMail(@Param(value = "uEmail")String uEmail);
    
    List<User> searchPageUser(@Param(value = "begain")Integer begain,@Param(value = "end")Integer end);
}