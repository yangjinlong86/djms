package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.FormulaDetails;

@Mapper
public interface FormulaDetailsMapper {
	int deleteByPrimaryKey(String id);

	int insert(FormulaDetails record);

	int insertSelective(FormulaDetails record);

	FormulaDetails selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(FormulaDetails record);

	int updateByPrimaryKey(FormulaDetails record);
}