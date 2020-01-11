package org.nocoder.djms.service;

import com.github.pagehelper.PageInfo;
import org.nocoder.djms.model.Customer;
import org.nocoder.djms.query.IQueryInfo;

public interface CustomerService {
    boolean saveOrUpdate(Customer customer);

    boolean deleteByCustomerId(String id);

    PageInfo<Customer> selectCustomer(IQueryInfo qb);

    boolean deleteByIds(String ids);
}
