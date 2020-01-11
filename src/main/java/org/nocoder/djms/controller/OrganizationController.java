package org.nocoder.djms.controller;

import org.apache.commons.lang3.StringUtils;
import org.nocoder.djms.enumeration.DBEnum;
import org.nocoder.djms.enumeration.ResponseEnum;
import org.nocoder.djms.model.Organization;
import org.nocoder.djms.service.OrganizationService;
import org.nocoder.djms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jason
 * @date 17/11/22.
 */
@Controller
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/findOrganization/{type}")
    @ResponseBody
    public String findOrganizations(@PathVariable String type) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        return JsonUtils.obj2json(organizationService.selectOrganizations(map));
    }

    @RequestMapping(value = "/getOrganizationCache")
    @ResponseBody
    public String findOrganizationCache() {
        return JsonUtils.obj2json(organizationService.getOrganizationsCache());
    }

    @RequestMapping(value = "/findOrganizationById/{id}")
    @ResponseBody
    public String findOrganizationById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return JsonUtils.obj2json(organizationService.selectOrganizationById(id));
    }

    @RequestMapping(value = "/findOrganizationsByPId/{pId}")
    @ResponseBody
    public String findOrganizationsByPId(@PathVariable String pId) {
        Map<String, String> map = new HashMap<>();
        map.put("pId", pId);
        return JsonUtils.obj2json(organizationService.selectOrganizations(map));
    }

    @RequestMapping(value = "/saveOrganization")
    @ResponseBody
    public String saveOrganization(Organization organization) {
        if (organization == null) {
            return ResponseEnum.FAILED.getStatus();
        }
        if (organizationService.saveOrUpdate(organization) >= DBEnum.OPERATION_SUCCESS.getValue()) {
            organizationService.initOranizations();
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }

    @RequestMapping(value = "/deleteOrganization/{id}")
    @ResponseBody
    public String deleteOrganizationById(@PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return JsonUtils.obj2json(organizationService.deleteById(id));
    }

}
