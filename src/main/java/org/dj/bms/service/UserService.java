package org.dj.bms.service;

import java.util.List;

import org.dj.bms.model.Role;
import org.dj.bms.model.User;

import com.github.pagehelper.PageInfo;

/**
 * @author Created by jason on 17/10/29.
 */
public interface UserService {

	User selectByUserId(String id);

	int saveOrUpdate(User user);

	List<Role> findCurrentUserRoles(User user);

	User findByUsername(String name);

	String getUserPassword(String id);

	int deleteByUserId(String id);

	PageInfo<User> selectAllUsers(Integer pageNum, Integer pageSize);

	User selectByUserName(String name);

}
