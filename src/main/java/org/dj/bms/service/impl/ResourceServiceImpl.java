package org.dj.bms.service.impl;

import org.dj.bms.dao.ResourceMapper;
import org.dj.bms.dao.RoleResourceMapper;
import org.dj.bms.model.Resource;
import org.dj.bms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jason on 17/11/3.
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Override
    public List<Resource> findResourcesByRoleId(String roleId) {
        //
        return roleResourceMapper.selectResourcesByRoleId(roleId);
    }
}
