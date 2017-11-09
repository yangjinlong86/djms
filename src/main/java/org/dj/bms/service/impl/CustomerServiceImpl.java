package org.dj.bms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.CustomerMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Customer;
import org.dj.bms.service.BmsService;
import org.dj.bms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: CustomerServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:28:04
 */
@Service("customerService")
public class CustomerServiceImpl extends BmsService implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public boolean saveOrUpdate(Customer customer) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isNotBlank(customer.getId())) {
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

}
