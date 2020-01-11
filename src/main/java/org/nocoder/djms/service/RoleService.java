package org.nocoder.djms.service;

import com.github.pagehelper.PageInfo;
import org.nocoder.djms.model.Role;
import org.nocoder.djms.model.RoleResource;
import org.nocoder.djms.query.RoleQueryBean;

import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
public interface RoleService {
    /**
     * 通过用户主键查询用户所有的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRoleListByUserId(String userId);

    PageInfo<Role> selectRoles(RoleQueryBean queryBean);

    Role findByRoleName(String roleName);

    int saveOrUpdate(Role role);

    int deleteRoleByIds(String ids);

    int saveRoleResource(RoleResource roleResource);

    int deleteRoleResource(String[] roleIdArr);
}
