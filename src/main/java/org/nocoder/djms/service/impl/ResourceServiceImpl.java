package org.nocoder.djms.service.impl;

import org.nocoder.djms.model.Resource;
import org.nocoder.djms.repository.ResourceMapper;
import org.nocoder.djms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Created by jason on 17/11/3.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> findResourcesByRoleId(String roleId) {
        return resourceMapper.selectResourcesByRoleId(roleId);
    }

    @Override
    public List<Resource> findResources() {
        return resourceMapper.selectResources();
    }

    @Override
    public List<Resource> findUserResources(Map<String, Object> paramMap) {
        return resourceMapper.selectUserResources(paramMap);
    }


}
