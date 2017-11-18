package org.dj.bms.controller;

import org.dj.bms.model.Resource;
import org.dj.bms.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 资源（权限）控制类
 *
 * @author jason
 * @date 17/11/17.
 */
@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findResourcesByRoleId/{roleId}")
    @ResponseBody
    public List<Resource> findResourceListByRoleId(@PathVariable String roleId) {
        return resourceService.findResourcesByRoleId(roleId);
    }
}
