package org.dj.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.FormulaDetailsMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.FormulaDetails;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.FormulaDetailsService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: FormulaDetailsServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:28:04
 */
@Service("formulaDetailsService")
public class FormulaDetailsServiceImpl extends BaseService implements FormulaDetailsService {
	@Autowired
	private FormulaDetailsMapper formulaDetailsMapper;

	@Override
	public boolean saveOrUpdate(FormulaDetails formula) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(formula.getId())) {
			formula.setId(IdGenerator.generateUUID());
			res = formulaDetailsMapper.insertSelective(formula);
		} else {
			res = formulaDetailsMapper.updateByPrimaryKeySelective(formula);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteById(String id) {
		return formulaDetailsMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<FormulaDetails> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<FormulaDetails>(formulaDetailsMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return formulaDetailsMapper.deleteByIds(idArr) == idArr.length;
	}

}
