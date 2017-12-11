package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.dj.bms.model.Consume;
import org.dj.bms.query.IQueryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ConsumeMapper {
	int deleteByPrimaryKey(String id);

	int insert(Consume record);

	int insertSelective(Consume record);

	Consume selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Consume record);

	int updateByPrimaryKey(Consume record);

	int deleteByIds(@Param("ids") String[] ids);

	List<Consume> select(IQueryInfo qb);

    List<Map<String, Object>> selectConsumptions(Map<String, Object> paramsMap);
}