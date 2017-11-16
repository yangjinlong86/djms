package org.dj.bms.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.Role;
import org.dj.bms.query.QueryBean;
import org.dj.bms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Created by jason on 17/11/11.
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findRoleListByUserId/{userId}")
    @ResponseBody
    public List<Role> findRoleListByRoleId(@PathVariable String userId){
        return roleService.findRoleListByUserId(userId);
    }

    /**
     * 分页查询
     * @param  bean
     * @return pageInfo
     */
    @RequestMapping("/selectRoles")
    @ResponseBody
    public PageInfo<Role> selectRoles(QueryBean bean){
        return roleService.selectRoles(bean);
    }

    @RequestMapping(value = "/saveRole")
    @ResponseBody
    public String save(Role role) {
        // 新建时,需要校验角色名是否已经存在
        if(StringUtils.isBlank(role.getId())){
            Role existsRole = roleService.findByRoleName(role.getRoleName());
            if (existsRole != null) {
                return ResponseEnum.ALREADY_EXISTS.getStatus();
            }
        }

        if (roleService.saveOrUpdate(role) > 0) {
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }


}
