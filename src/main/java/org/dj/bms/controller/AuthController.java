package org.dj.bms.controller;

import org.dj.bms.service.OrganizationService;
import org.dj.bms.service.ResourceService;
import org.dj.bms.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 授权相关控制类
 *
 * @author jason
 * @date 17/11/3
 */
@Controller
public class AuthController {
	@Autowired
	private ResourceService resourceService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/resource")
    public String resource(Model model) {
        model.addAttribute("resources", JsonUtils.obj2json(resourceService.findResources()));
        return "authorize/resource";
    }

	@RequestMapping("/role")
	public String role(Model model) {
		model.addAttribute("resources", JsonUtils.obj2json(resourceService.findResources()));
		return "authorize/role";
	}

	@RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("organizations", JsonUtils.obj2json(organizationService.selectOrganizations(null)));
        return "authorize/user";
	}

    @RequestMapping("/user-setting")
    public String userSetting() {
        return "authorize/user-setting";
    }

    @RequestMapping("/organization")
    public String organization(Model model) {
        model.addAttribute("organizations", JsonUtils.obj2json(organizationService.selectOrganizations(null)));
        return "authorize/organization";
    }

    @RequestMapping("/noauth")
    public String noauth() {
        return "noauth";
    }

}
