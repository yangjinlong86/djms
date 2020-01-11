package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.nocoder.djms.model.Customer;
import org.nocoder.djms.query.IQueryInfo;
import org.nocoder.djms.query.QueryBean;

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