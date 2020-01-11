package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YANGJINLONG
 */
@Mapper
@Repository
public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectUserRoleByUserId(String userId);

    int deleteUserRoleByUserId(String userId);

    int deleteUserRoleByUserIds(String[] userIdArr);
}