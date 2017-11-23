package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Stock;

@Mapper
public interface StockMapper {
	int deleteByPrimaryKey(String id);

	int insert(Stock record);

	int insertSelective(Stock record);

	Stock selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Stock record);

	int updateByPrimaryKey(Stock record);
}