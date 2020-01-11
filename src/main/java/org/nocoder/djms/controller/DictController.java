package org.nocoder.djms.controller;

import org.apache.commons.lang3.StringUtils;
import org.nocoder.djms.model.Dict;
import org.nocoder.djms.utils.ECacheUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author pufangfei@163.com
 * @ClassName: DictController
 * @Description: TODO
 * @date 2017年11月11日 上午11:14:14
 */
@RestController
public class DictController extends BaseController {

    @Value("${select.limit}")
    private int limit = 10;

    /**
     * @param pid  字典类型
     * @param key  关键字
     * @param size 返回条数 默认为 10条
     * @return Object
     * @throws
     * @Title: testEache
     * @Description: 通过关键字得到字典的值
     */
    @RequestMapping("dict/getValues/{pid}/{size}")
    public Object dictEache(@PathVariable("pid") String pid, @PathVariable("size") int size, String term) {
        Map<String, Dict> dict = ECacheUtils.getDictCache();
        List<Dict> nodes = new ArrayList<>();
        int sum = size == 0 ? limit : size;
        dict.forEach((k, v) -> {
            if (StringUtils.startsWith(k, pid)) {
                if (StringUtils.contains(k, term)) {
                    if (sum == nodes.size()) {
                        return;
                    }
                    nodes.add(v);
                }
            }
        });
        return nodes;
    }
}
