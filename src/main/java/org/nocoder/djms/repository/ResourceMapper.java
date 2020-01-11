package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 */
@Mapper
@Repository
public interface ResourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectResources();

    List<Resource> selectUserResources(Map<String, Object> paramMap);

    List<Resource> selectResourcesByRoleId(String roleId);
}