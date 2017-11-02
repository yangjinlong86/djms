package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.RoleResource;
@Mapper
public interface RoleResourceMapper {
    int insert(RoleResource record);

    int insertSelective(RoleResource record);
}