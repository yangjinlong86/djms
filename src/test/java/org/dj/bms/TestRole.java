package org.dj.bms;

import org.dj.bms.dao.RoleMapper;
import org.dj.bms.model.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author  Created by jason on 17/11/11.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BmsApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestRole {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testRoleMapper(){
        List<Role> roleList =  roleMapper.findRoleListByUserId("b6136ad5fbc84256a074e116ec3a0d07");
        System.out.println("拥有角色个数:" + roleList.size());
    }
}
