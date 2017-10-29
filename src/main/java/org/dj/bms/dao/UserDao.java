package org.dj.bms.dao;

import org.dj.bms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jason
 * Created by jason on 17/10/29.
 */

public interface UserDao extends JpaRepository<User, String> {
    @Override
    User save(User user);
}
