package org.dj.bms.cache;

import org.dj.bms.enumeration.ECacheEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @ClassName: DictCache
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:54:45
 */
@Component
public class DictCache implements CommandLineRunner {

	@Autowired
	private CacheManager cacheManager;

	@Override
	public void run(String... arg0) throws Exception {
		if (!cacheManager.cacheExists(ECacheEnum.DICTCACHE.getValue())) {
			cacheManager.addCache(ECacheEnum.DICTCACHE.getValue());
		}
		Cache cache = cacheManager.getCache(ECacheEnum.DICTCACHE.getValue());
		Element element = new Element("xx", "123123");
		cache.put(element);
	}

}
