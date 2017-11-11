package org.dj.bms.utils;

import java.util.Map;

import org.dj.bms.enumeration.ECacheEnum;
import org.dj.bms.model.Node;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @ClassName: ECacheUtils
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 下午3:11:56
 */

public class ECacheUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Node> getDictCache() {
		String dictCacheName = ECacheEnum.DICTCACHE.getValue();
		return (Map<String, Node>) getCacheElement(dictCacheName).getObjectValue();
	}

	public static Element getCacheElement(String cacheName) {
		CacheManager cacheM = CacheManager.getInstance();
		if (cacheM.cacheExists(cacheName)) {
			Cache cache = cacheM.getCache(cacheName);
			Element element = cache.get(cacheName);
			if (element != null && element.getObjectValue() != null) {
				return element;
			}
		}
		return null;
	}
}
