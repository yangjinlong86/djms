package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author  jason
 * @date 2017年11月22日
 */
@Mapper
@Repository
public interface OrganizationMapper {

    int deleteByPrimaryKey(String id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(String id);

    List<Organization> selectOrganizations(Map<String, String> paramsMap);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

}