package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Consume;

@Mapper
public interface ConsumeMapper {
	int deleteByPrimaryKey(String id);

	int insert(Consume record);

	int insertSelective(Consume record);

	Consume selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Consume record);

	int updateByPrimaryKey(Consume record);
}