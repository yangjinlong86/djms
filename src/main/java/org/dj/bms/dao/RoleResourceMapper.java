package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Resource;
import org.dj.bms.model.RoleResource;

import java.util.List;
@Mapper
public interface RoleResourceMapper {
    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<Resource> selectResourcesByRoleId(String roleId);
}