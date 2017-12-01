package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.VarietyMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Variety;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.VarietyService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: VarietyServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月29日 下午11:40:31
 */
@Service("varietyService")
public class VarietyServiceImpl extends BaseService implements VarietyService {

	@Autowired
	private VarietyMapper varietyMapper;

	@Override
	public boolean saveOrUpdate(Variety variety) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(variety.getId())) {
			variety.setId(IdGenerator.generateUUID());
			res = varietyMapper.insertSelective(variety);
		} else {
			res = varietyMapper.updateByPrimaryKeySelective(variety);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteById(String id) {
		return varietyMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Variety> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Variety>(varietyMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return varietyMapper.deleteByIds(idArr) == idArr.length;
	}

}
