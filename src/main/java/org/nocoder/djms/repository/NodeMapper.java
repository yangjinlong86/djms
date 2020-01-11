package org.nocoder.djms.repository;

import org.apache.ibatis.annotations.Mapper;
import org.nocoder.djms.model.Dict;
import org.nocoder.djms.model.Node;

import java.util.List;

/**
 * @author pufangfei@163.com
 * @ClassName: NodeMapper
 * @Description: TODO
 * @date 2017年11月11日 上午10:19:06
 */
@Mapper
public interface NodeMapper {
    /**
     * 加载所有数据字典
     */
    List<Node> loadAllDictNode();

    List<Dict> loadAllDict();
}
