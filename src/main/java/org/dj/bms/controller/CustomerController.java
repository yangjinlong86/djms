package org.dj.bms.controller;

import org.dj.bms.enumeration.ECacheEnum;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @ClassName: CustomerController
 * @Description: 顾客控制层
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:58:45
 */
@RestController
public class CustomerController extends BaseController {
	@Autowired
	private CacheManager cacheManager;

	@RequestMapping("test/{key}")
	public Object testEache(@PathVariable("key") String key) {
		Cache cache = cacheManager.getCache(ECacheEnum.DICTCACHE.getValue());
		ResponseMsg res = getRes(true);
		Element e = cache.get(key);
		res.setData(e.getObjectValue());
		return res;
	}
}
