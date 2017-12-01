package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Order;
import org.dj.bms.query.IQueryInfo;

import java.util.List;

@Mapper
public interface OrderMapper {
	int deleteByPrimaryKey(String id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);

	int deleteByIds(@Param("ids") String[] ids);

	List<Order> select(IQueryInfo qb);
}