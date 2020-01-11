package org.nocoder.djms;

import org.nocoder.djms.repository.RoleMapper;
import org.nocoder.djms.repository.RoleResourceMapper;
import org.nocoder.djms.model.RoleResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by jason on 17/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = DJMSApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestRole {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper rrMapper;

    @Test
    public void testRoleMapper() {
        //List<Role> roleList = roleMapper.findRoleListByUserId("b6136ad5fbc84256a074e116ec3a0d07");

        List<RoleResource> rrList = new ArrayList<>();
        RoleResource rr1 = new RoleResource();
        rr1.setRoleId("aa");
        rr1.setResourceId("AA");
        RoleResource rr2 = new RoleResource();
        rr2.setRoleId("bb");
        rr2.setResourceId("BB");
        rrList.add(rr1);
        rrList.add(rr2);
        assert (rrMapper.insert(rrList) > 0);
    }
}
