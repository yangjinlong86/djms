package org.dj.bms.controller;

import org.dj.bms.model.Role;
import org.dj.bms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jason on 17/11/11.
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findRoleListByUserId/{userId}")
    @ResponseBody
    public List<Role> findRoleListByUserId(@PathVariable String userId){
        return roleService.findRoleListByUserId(userId);
    }

    @RequestMapping("/findAllRoles")
    @ResponseBody
    public List<Role> findAllRoles(Model model){
        List<Role> roleList = roleService.findAllRoles();
        model.addAttribute("allRoles", roleList);
        return roleList;
    }
}
