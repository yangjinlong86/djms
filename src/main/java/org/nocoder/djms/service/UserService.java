package org.nocoder.djms.service;

import com.github.pagehelper.PageInfo;
import org.nocoder.djms.model.Role;
import org.nocoder.djms.model.User;
import org.nocoder.djms.query.UserQueryBean;

import java.util.List;

/**
 * @author Created by jason on 17/10/29.
 */
public interface UserService {

    /**
     * 根据主键查询用户
     *
     * @param id
     * @return
     */
    User selectByUserId(String id);

    /**
     * 保存或更新用户
     *
     * @param user
     * @return
     */
    int saveOrUpdate(User user);

    /**
     * 查询当前用的拥有的角色
     *
     * @param user
     * @return
     */
    List<Role> findCurrentUserRoles(User user);

    /**
     * 根据用户名查找用户
     *
     * @param name
     * @return
     */
    User findByUsername(String name);

    int countByUsername(User user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteByUserId(String id);

    /**
     * 批量删除用户
     *
     * @param idArr
     * @return
     */
    int deleteByUserIds(String[] idArr);

    /**
     * 查询用户
     *
     * @param userQueryBean
     * @return
     */
    PageInfo<User> selectUsers(UserQueryBean userQueryBean);

    /**
     * 保存用户角色对应关系
     *
     * @param user
     * @return
     */
    int saveUserRole(User user);

    boolean validatePassword(User user);
}
