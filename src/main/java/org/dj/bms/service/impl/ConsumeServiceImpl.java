package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.ConsumeMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Consume;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.ConsumeService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ConsumeServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:28:04
 */
@Service("consumeService")
public class ConsumeServiceImpl extends BaseService implements ConsumeService {
	@Autowired
	private ConsumeMapper consumeMapper;

	@Override
	public boolean saveOrUpdate(Consume consume) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(consume.getId())) {
			consume.setId(IdGenerator.generateUUID());
			res = consumeMapper.insertSelective(consume);
		} else {
			res = consumeMapper.updateByPrimaryKeySelective(consume);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteById(String id) {
		return consumeMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Consume> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Consume>(consumeMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return consumeMapper.deleteByIds(idArr) == idArr.length;
	}

}
