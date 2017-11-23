package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.StockUse;

@Mapper
public interface StockUseMapper {
	int deleteByPrimaryKey(String id);

	int insert(StockUse record);

	int insertSelective(StockUse record);

	StockUse selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(StockUse record);

	int updateByPrimaryKey(StockUse record);
}