package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Product;
import org.dj.bms.query.IQueryInfo;

import java.util.List;

@Mapper
public interface ProductMapper {
	int deleteByPrimaryKey(String id);

	int insert(Product record);

	int insertSelective(Product record);

	Product selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);

	int deleteByIds(@Param("ids") String[] idArr);

	List<Product> selectProduct(IQueryInfo qb);
}