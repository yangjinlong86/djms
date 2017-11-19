package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Order;

@Mapper
public interface OrderMapper {
	int deleteByPrimaryKey(String id);

	int insert(Order record);

	int insertSelective(Order record);

	Order selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Order record);

	int updateByPrimaryKey(Order record);
}