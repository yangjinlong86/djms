package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.UserRole;

import java.util.List;

/**
 *@author YANGJINLONG
 */
@Mapper
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectUserRoleByUserId(String userId);

    int deleteUserRoleByUserId(String userId);
}