package org.dj.bms.service;

import org.dj.bms.model.Role;
import org.dj.bms.model.User;

import java.util.List;

/**
 * Created by jason on 17/10/29.
 */
public interface UserService {

    int save(User user);

    List<Role> findCurrentUserRoles(User user);

    User findByUsername(String name);

    String getUserPassword(String id);
}
