package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Dict;

@Mapper
public interface DictMapper {
	int deleteByPrimaryKey(String id);

	int insert(Dict record);

	int insertSelective(Dict record);

	Dict selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Dict record);

	int updateByPrimaryKey(Dict record);

}