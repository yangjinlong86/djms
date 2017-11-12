package org.dj.bms;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by jason on 17/11/12.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BmsApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestCache {
    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testEcache() {
        String name = "testCACHE";
        if (!cacheManager.cacheExists(name)) {
            cacheManager.addCache(name);
        }
        Cache cache = cacheManager.getCache(name);
        Object v = cache.get("xx").getObjectValue();
        System.out.println(v.toString());

    }
}
