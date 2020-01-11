package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.Dict;

@Mapper
public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

}