package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.StockMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Stock;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.StockService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: StockServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月22日 下午9:35:54
 */
@Service("stockService")
public class StockServiceImpl extends BaseService implements StockService {
	@Autowired
	private StockMapper stockMapper;

	@Override
	public boolean saveOrUpdate(Stock stock) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(stock.getId())) {
			stock.setId(IdGenerator.generateUUID());
			res = stockMapper.insertSelective(stock);
		} else {
			res = stockMapper.updateByPrimaryKeySelective(stock);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Stock> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Stock>(stockMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return stockMapper.deleteByIds(idArr) == idArr.length;
	}

}
