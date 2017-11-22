package org.dj.bms.dao;

import org.dj.bms.model.Variety;

public interface VarietyMapper {
    int deleteByPrimaryKey(String id);

    int insert(Variety record);

    int insertSelective(Variety record);

    Variety selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Variety record);

    int updateByPrimaryKey(Variety record);
}