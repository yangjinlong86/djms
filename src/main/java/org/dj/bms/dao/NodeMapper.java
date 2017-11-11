package org.dj.bms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.Dict;
import org.dj.bms.model.Node;

/**
 * @ClassName: NodeMapper
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午10:19:06
 */
@Mapper
public interface NodeMapper {
	/**
	 * 加载所有数据字典
	 * 
	 */
	List<Node> loadAllDictNode();

	List<Dict> loadAllDict();
}
