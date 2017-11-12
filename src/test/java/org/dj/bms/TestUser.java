package org.dj.bms;

import java.util.Calendar;

import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.dj.bms.utils.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author Created by jason on 17/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BmsApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestUser {
    @Autowired
    private UserService userService;

    @Test
    public void testSaveOrUpdate() {
        for (int i = 0; i<100; i++) {
            User user = new User();
            user.setName("user"+i);
            user.setPassword("123");
            user.setRealName("美容师"+i);
            user.setCorpId("1");
            user.setDeptId("2");
            user.setCreateTime(Calendar.getInstance().getTime());
            user.setRoleValues("1");
            userService.saveOrUpdate(user);
        }
    }
}
