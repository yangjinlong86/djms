package org.dj.bms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.ProductMapper;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.model.Product;
import org.dj.bms.query.IQueryInfo;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.ProductService;
import org.dj.bms.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月16日 下午10:42:35
 */
@Service("productService")
public class ProductServiceImpl extends BaseService implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	public boolean saveOrUpdate(Product product) {
		int res = 0;
		// 主键为空 新增
		if (StringUtils.isBlank(product.getId())) {
			product.setId(IdGenerator.generateUUID());
			res = productMapper.insertSelective(product);
		} else {
			res = productMapper.updateByPrimaryKeySelective(product);
		}
		return res == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public boolean deleteByProductId(String id) {
		return productMapper.deleteByPrimaryKey(id) == DBEnum.OPERATION_SUCCESS.getValue();
	}

	@Override
	public PageInfo<Product> selectProduct(IQueryInfo qb) {
		PageHelper.startPage(qb.getPageNum(), qb.getPageSize()).setOrderBy(qb.getOrderBy());
		return new PageInfo<Product>(productMapper.selectProduct(qb));
	}

	@Override
	public boolean deleteByIds(String ids) {
		String[] idArr = ids.split(";");
		return productMapper.deleteByIds(idArr) == idArr.length;
	}

}
