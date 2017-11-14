package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Role;
import org.dj.bms.query.QueryBean;

import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
public interface RoleService {
    List<Role> findRoleListByUserId(String userId);

    List<Role> findAllRoles();

    PageInfo<Role> selectRoles(QueryBean queryBean);

    Role findByRoleName(String roleName);

    int saveOrUpdate(Role role);
}
