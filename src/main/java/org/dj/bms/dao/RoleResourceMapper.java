package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Resource;
import org.dj.bms.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author YANGJINLONG
 */
@Mapper
@Repository
public interface RoleResourceMapper {
    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<Resource> selectResourcesByRoleId(String roleId);
}