package org.dj.bms.controller;

import org.dj.bms.service.ResourceService;
import org.dj.bms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资源（权限）控制类
 * @author jason
 * @date 17/11/17.
 */
@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/resource")
    public String resource(Model model) {
        model.addAttribute("resources", JsonUtils.obj2json(resourceService.findResources()));
        return "authorize/resource";
    }



}
