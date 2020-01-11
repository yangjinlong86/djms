package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.RoleResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YANGJINLONG
 */
@Mapper
@Repository
public interface RoleResourceMapper {
    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    int insert(List<RoleResource> list);

    int insertSelective(RoleResource record);

    /**
     * 批量删除
     *
     * @param roleIdArr
     * @return
     */
    int deleteRoleResource(String[] roleIdArr);
}