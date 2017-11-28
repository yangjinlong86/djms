package org.dj.bms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Stock;
import org.dj.bms.query.IQueryInfo;

@Mapper
public interface StockMapper {
	int deleteByPrimaryKey(String id);

	int insert(Stock record);

	int insertSelective(Stock record);

	Stock selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Stock record);

	int updateByPrimaryKey(Stock record);

	List<Stock> select(IQueryInfo qb);

	int deleteByIds(@Param("ids") String[] ids);
}