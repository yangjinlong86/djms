package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 */
@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(String id);

    int deleteByUserIds(String[] idArray);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String name);

    List<User> selectUsers(Map<String, String> paramsMap);

    int selectCountUser();

    int countByUsername(User user);

}