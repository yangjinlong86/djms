package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author YANGJINLONG
 */
@Mapper
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int deleteByIds(String[] idArr);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> findRoleListByUserId(String userId);

    List<Role> findRoles(Map<String, String> paramsMap);

}