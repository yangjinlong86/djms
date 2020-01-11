package org.nocoder.djms.service;

import org.nocoder.djms.model.Organization;

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

    void initOranizations();

    List<Organization> getOrganizationsCache();

    Map<String, Organization> getOrganizationsCacheMap();

}
