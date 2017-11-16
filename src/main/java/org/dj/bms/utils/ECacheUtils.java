package org.dj.bms.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.enumeration.DictEnum;
import org.dj.bms.enumeration.ECacheEnum;
import org.dj.bms.model.Dict;

import java.util.Map;

/**
 * @ClassName: ECacheUtils
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月11日 下午3:11:56
 */

public class ECacheUtils {

	@SuppressWarnings("unchecked")
	public static Map<String, Dict> getDictCache() {
		String dictCacheName = ECacheEnum.DICTCACHE.getValue();
		return (Map<String, Dict>) getCacheElement(dictCacheName).getObjectValue();
	}

	public static String getCodeName(DictEnum dict, String code) {
		String key = dict.getCode() + "|" + code + "|";
		String name = "";
		Map<String, Dict> dictCache = getDictCache();
		for (String k : dictCache.keySet()) {
			if (StringUtils.startsWith(k, key)) {
				name = dictCache.get(k).getName();
				break;
			}
		}
		return name;

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
