package org.dj.bms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by jason on 17/10/29.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/account-info")
    public String home(Model model) {

        String name = "World";

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        if (principalCollection != null && !principalCollection.isEmpty()) {
            name = principalCollection.getPrimaryPrincipal().toString();
        }

        model.addAttribute("name", name);

        return "account-info";
    }

    @RequestMapping("/saveUser")
    public String save(Model model){
        // TODO 测试代码
        User user = new User();
        user.setId(UUID.randomUUID().toString().replace("-",""));
        user.setName("Jason Yang");

        userService.save(user);
        return "index";
    }
}
