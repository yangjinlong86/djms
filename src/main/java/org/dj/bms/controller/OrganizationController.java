package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.Organization;
import org.dj.bms.service.OrganizationService;
import org.dj.bms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jason
 * @date 17/11/22.
 */
@Controller
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value="/findOrganization")
    @ResponseBody
    public String findOrganizations(){
        return JsonUtils.obj2json(organizationService.selectOrganizations(null));
    }

    @RequestMapping(value="/findOrganization/{id}")
    @ResponseBody
    public String findOrganizationById(@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        return JsonUtils.obj2json(organizationService.selectOrganizationById(id));
    }

    @RequestMapping(value="/saveOrganization")
    @ResponseBody
    public String saveOrganization(Organization organization){
        if(organization == null){
            return ResponseEnum.FAILED.getStatus();
        }
        if(organizationService.saveOrUpdate(organization) >= DBEnum.OPERATION_SUCCESS.getValue()) {
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }

    @RequestMapping(value="/deleteOrganization/{id}")
    @ResponseBody
    public String deleteOrganizationById(@PathVariable String id){
        if(StringUtils.isBlank(id)){
            return null;
        }
        return JsonUtils.obj2json(organizationService.deleteById(id));
    }

}
