package org.dj.bms.service.impl;

import org.dj.bms.dao.RoleMapper;
import org.dj.bms.model.Role;
import org.dj.bms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by jason on 17/11/3.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleListByUserId(String userId) {
        return roleMapper.findRoleListByUserId(userId);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAllRoles();
    }
}
