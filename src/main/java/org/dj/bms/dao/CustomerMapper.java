package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Customer;

@Mapper
public interface CustomerMapper {
	int deleteByPrimaryKey(String id);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);
}