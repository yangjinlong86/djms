package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.FormulaDetails;
import org.dj.bms.query.IQueryInfo;

import java.util.List;

@Mapper
public interface FormulaDetailsMapper {
	int deleteByPrimaryKey(String id);

	int insert(FormulaDetails record);

	int insertSelective(FormulaDetails record);

	FormulaDetails selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(FormulaDetails record);

	int updateByPrimaryKey(FormulaDetails record);

	int deleteByIds(@Param("ids") String[] ids);

	List<FormulaDetails> select(IQueryInfo qb);
}