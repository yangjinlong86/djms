package org.dj.bms.service.impl;

import org.dj.bms.dao.UserDao;
import org.dj.bms.entity.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by jason on 17/10/29.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public User save(User user) {
        return userDao.save(user);
    }
}
