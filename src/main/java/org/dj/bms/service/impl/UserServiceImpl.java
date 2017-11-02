package org.dj.bms.service.impl;

import org.dj.bms.dao.UserMapper;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 17/10/29.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<Role> findCurrentUserRoles(User user) {
        // TODO 查询当前用户拥有的角色
        return null;
    }


    @Override
    public User findByUsername(String name) {
        // TODO 根据用户名查询用户
        return null;
    }

    @Override
    public String getUserPassword(String id) {
        // TODO 根据用户ID查询用户密码
        return null;
    }
}
