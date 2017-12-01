package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Formula;
import org.dj.bms.query.IQueryInfo;

import java.util.List;

@Mapper
public interface FormulaMapper {
	int deleteByPrimaryKey(String id);

	int insert(Formula record);

	int insertSelective(Formula record);

	Formula selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Formula record);

	int updateByPrimaryKey(Formula record);

	int deleteByIds(@Param("ids") String[] ids);

	List<Formula> select(IQueryInfo qb);
}