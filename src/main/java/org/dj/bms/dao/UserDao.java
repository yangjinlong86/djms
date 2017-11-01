package org.dj.bms.dao;

import org.apache.ibatis.annotations.Mapper;
import org.dj.bms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jason
 * Created by jason on 17/10/29.
 */
@Mapper
public interface UserDao extends JpaRepository<User, String> {
    @Override
    User save(User user);
}
