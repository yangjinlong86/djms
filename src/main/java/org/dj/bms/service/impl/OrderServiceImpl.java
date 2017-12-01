package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.OrderMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Order;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.OrderService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:28:04
 */
@Service("orderService")
public class OrderServiceImpl extends BaseService implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public boolean saveOrUpdate(Order order) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(order.getId())) {
			order.setId(IdGenerator.generateUUID());
			res = orderMapper.insertSelective(order);
		} else {
			res = orderMapper.updateByPrimaryKeySelective(order);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteById(String id) {
		return orderMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Order> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Order>(orderMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return orderMapper.deleteByIds(idArr) == idArr.length;
	}

}
