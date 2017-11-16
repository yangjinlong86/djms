package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Formula;

@Mapper
public interface FormulaMapper {
	int deleteByPrimaryKey(String id);

	int insert(Formula record);

	int insertSelective(Formula record);

	Formula selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Formula record);

	int updateByPrimaryKey(Formula record);
}