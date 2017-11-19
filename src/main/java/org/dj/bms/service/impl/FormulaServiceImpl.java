package org.dj.bms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.FormulaMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Formula;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.FormulaService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: FormulaServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:28:04
 */
@Service("formulaService")
public class FormulaServiceImpl extends BaseService implements FormulaService {
	@Autowired
	private FormulaMapper formulaMapper;

	@Override
	public boolean saveOrUpdate(Formula formula) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(formula.getId())) {
			formula.setId(IdGenerator.generateUUID());
			res = formulaMapper.insertSelective(formula);
		} else {
			res = formulaMapper.updateByPrimaryKeySelective(formula);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteById(String id) {
		return formulaMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Formula> select(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Formula>(formulaMapper.select(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return formulaMapper.deleteByIds(idArr) == idArr.length;
	}

}
