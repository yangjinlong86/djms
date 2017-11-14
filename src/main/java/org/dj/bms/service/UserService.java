package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Role;
import org.dj.bms.model.User;
import org.dj.bms.query.UserQueryBean;

import java.util.List;

/**
 * @author Created by jason on 17/10/29.
 */
public interface UserService{

	User selectByUserId(String id);

	int saveOrUpdate(User user);

	List<Role> findCurrentUserRoles(User user);

	User findByUsername(String name);

	String getUserPassword(String id);

	int deleteByUserId(String id);

    int deleteByUserIds(String[] idArr);

	PageInfo<User> selectUsers(UserQueryBean userQueryBean);

	User selectByUserName(String name);

	int selectCountUser();

}
