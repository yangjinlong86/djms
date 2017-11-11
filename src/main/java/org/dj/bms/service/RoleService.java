package org.dj.bms.service;

import org.dj.bms.model.Role;

import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
public interface RoleService {
    List<Role> findRoleListByUserId(String userId);

    List<Role> findAllRoles();
}
