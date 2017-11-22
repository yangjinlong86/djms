package org.dj.bms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.dao.OrganizationMapper;
import org.dj.bms.model.Organization;
import org.dj.bms.service.BaseService;
import org.dj.bms.service.OrganizationService;
import org.dj.bms.utils.IdGenerator;
import org.dj.bms.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 17/11/22.
 */
@Service
public class OrganizationServiceImpl extends BaseService implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public int deleteById(String id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int saveOrUpdate(Organization organization) {
        if(StringUtils.isBlank(organization.getId())){
            organization.setId(IdGenerator.generateUUID());
            organization.setCreateTime(new Date());
            organization.setCreateUser(UserUtils.getCurrentUser().getId());
            return organizationMapper.insertSelective(organization);
        }
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    @Override
    public Organization selectOrganizationById(String id) {
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Organization> selectOrganizations(Map<String, String> paramsMap) {
        return organizationMapper.selectOrganizations(paramsMap);
    }
}
