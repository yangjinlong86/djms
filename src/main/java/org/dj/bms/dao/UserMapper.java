package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.User;

import java.util.List;
import java.util.Map;

/**
 *@author jason
 */
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);

    List<User> selectUsers(Map<String,String> paramsMap);

    int selectCountUser();

}