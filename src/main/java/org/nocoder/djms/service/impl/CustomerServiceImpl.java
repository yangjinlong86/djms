package org.nocoder.djms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.nocoder.djms.enumeration.DBEnum;
import org.nocoder.djms.model.Customer;
import org.nocoder.djms.query.IQueryInfo;
import org.nocoder.djms.repository.CustomerMapper;
import org.nocoder.djms.service.BaseService;
import org.nocoder.djms.service.CustomerService;
import org.nocoder.djms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pufangfei@163.com
 * @ClassName: CustomerServiceImpl
 * @Description: TODO
 * @date 2017年11月8日 下午11:28:04
 */
@Service("customerService")
public class CustomerServiceImpl extends BaseService implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public boolean saveOrUpdate(Customer customer) {
        int res = 0;
        // 主键为空 新增
        if (StringUtils.isBlank(customer.getId())) {
            customer.setId(IdGenerator.generateUUID());
            res = customerMapper.insertSelective(customer);
        } else {
            res = customerMapper.updateByPrimaryKeySelective(customer);
        }
        return res == DBEnum.OPERATION_SUCCESS.getValue();
    }

    @Override
    public boolean deleteByCustomerId(String id) {
        return customerMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
    }

    @Override
    public PageInfo<Customer> selectCustomer(IQueryInfo qb) {
        PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
        return new PageInfo<Customer>(customerMapper.selectCustomer(qb));
    }

    @Override
    public boolean deleteByIds(String ids) {
        String[] idArr = ids.split(";");
        return customerMapper.deleteByIds(idArr) == idArr.length;
    }

}
