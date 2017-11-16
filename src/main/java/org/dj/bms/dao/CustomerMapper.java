package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Customer;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.query.QueryBean;

import java.util.List;

@Mapper
public interface CustomerMapper {
	int deleteByPrimaryKey(String id);

	int insert(Customer record);

	int insertSelective(Customer record);

	Customer selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Customer record);

	int updateByPrimaryKey(Customer record);

	List<Customer> selectCustomer(IQueryInfo qb);

	int selectCustomerCount(QueryBean qb);

	int deleteByIds(@Param("ids") String[] ids);
}