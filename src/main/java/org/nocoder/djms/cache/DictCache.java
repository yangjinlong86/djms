package org.nocoder.djms.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nocoder.djms.enumeration.ECacheEnum;
import org.nocoder.djms.model.Dict;
import org.nocoder.djms.repository.NodeMapper;
import org.nocoder.djms.utils.PingyinUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pufangfei@163.com
 * @ClassName: DictCache
 * @Description: TODO
 * @date 2017年11月11日 上午9:54:45
 */
@Component("dictCache")
public class DictCache implements CommandLineRunner {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public void run(String... arg0) throws Exception {
        String cacheName = ECacheEnum.DICTCACHE.getValue();
        if (!cacheManager.cacheExists(cacheName)) {
            cacheManager.addCache(cacheName);
        }
        Map<String, Dict> dictCache = new HashMap<>();
        Cache cache = cacheManager.getCache(cacheName);
        List<Dict> nodes = nodeMapper.loadAllDict();
        for (Dict dict : nodes) {
            dictCache.put(getKey(dict), dict);
        }
        cache.remove(cacheName);
        cache.put(new Element(cacheName, dictCache));
    }

    public String getKey(Dict dict) {
        StringBuilder sb = new StringBuilder();
        String name = dict.getName();
        sb.append(dict.getPid() + "|");
        sb.append(dict.getCode() + "|");
        sb.append(PingyinUtils.convert2qp(name) + "|");
        sb.append(PingyinUtils.convert2jp(name) + "|");
        sb.append(name + "|");
        return sb.toString();
    }

}
