package org.dj.bms.controller;

import org.dj.bms.enumeration.ECacheEnum;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @ClassName: CustomerController
 * @Description: 顾客控制层
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:58:45
 */
@Controller
public class CustomerController extends BaseController {
	@Autowired
	private CacheManager cacheManager;

	@RequestMapping("test/{key}")
	public Object testEache(@PathVariable("key") String key) {
		Cache cache = cacheManager.getCache(ECacheEnum.DICTCACHE.getValue());
		ResponseMsg res = getRes(true);
		res.setData(cache.get(key).getObjectValue());
		return res;
	}
}
