package org.dj.bms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.StockUseMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.StockUse;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.StockUseService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: StockServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月22日 下午9:35:54
 */
@Service("stockUseService")
public class StockUseServiceImpl extends BaseService implements StockUseService {
	@Autowired
	private StockUseMapper stockUseMapper;

	@Override
	public boolean saveOrUpdate(StockUse stockUse) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(stockUse.getId())) {
			stockUse.setId(IdGenerator.generateUUID());
			res = stockUseMapper.insertSelective(stockUse);
		} else {
			res = stockUseMapper.updateByPrimaryKeySelective(stockUse);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<StockUse> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<StockUse>(stockUseMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return stockUseMapper.deleteByIds(idArr) == idArr.length;
	}

}
