package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Variety;
import org.dj.bms.query.IQueryInfo;

import java.util.List;

@Mapper
public interface VarietyMapper {
	int deleteByPrimaryKey(String id);

	int insert(Variety record);

	int insertSelective(Variety record);

	Variety selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Variety record);

	int updateByPrimaryKey(Variety record);

	List<Variety> select(IQueryInfo qb);

	int deleteByIds(@Param("ids") String[] ids);
}