package org.dj.bms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.StockUse;
import org.dj.bms.query.IQueryInfo;

public interface StockUseMapper {
	int deleteByPrimaryKey(String id);

	int insert(StockUse record);

	int insertSelective(StockUse record);

	StockUse selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(StockUse record);

	int updateByPrimaryKey(StockUse record);

	int deleteByIds(@Param("ids") String[] idArr);

	List<StockUse> select(IQueryInfo qb);
}