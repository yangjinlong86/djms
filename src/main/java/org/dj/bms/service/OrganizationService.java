package org.dj.bms.service;

import org.dj.bms.model.Organization;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 17/11/22.
 */
public interface OrganizationService {
    int deleteById(String id);

    int saveOrUpdate(Organization record);

    Organization selectOrganizationById(String id);

    List<Organization> selectOrganizations(Map<String, String> paramsMap);

}
