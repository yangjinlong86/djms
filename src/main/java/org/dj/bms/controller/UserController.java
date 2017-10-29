package org.dj.bms.controller;

import org.dj.bms.entity.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jason on 17/10/29.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/saveUser")
    public String save(Model model){
        // TODO 测试代码
        User user = new User(UUID.randomUUID().toString().replace("-",""),"zs","a","a",new Date());
        userService.save(user);
        return "index";
    }
}
