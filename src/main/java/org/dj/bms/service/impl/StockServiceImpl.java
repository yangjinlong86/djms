package org.dj.bms.service.impl;

import org.dj.bms.model.Stock;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.StockService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * @ClassName: StockServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月22日 下午9:35:54
 */
@Service("stockService")
public class StockServiceImpl extends BaseService implements StockService {

	@Override
	public boolean saveOrUpdate(Stock stock) {
		return false;
	}

	@Override
	public PageInfo<Stock> select(IQueryInfo qb) {
		return null;
	}

	@Override
	public boolean deleteByIds(String ids) {
		return false;
	}

}
