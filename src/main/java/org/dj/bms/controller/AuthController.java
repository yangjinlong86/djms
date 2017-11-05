package org.dj.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jason on 17/11/3.
 */
@Controller
public class AuthController {

    @RequestMapping("/resource")
    public String resource(){
        return "resource";
    }

    @RequestMapping("/role")
    public String role(){
        return "role";
    }

    @RequestMapping("/user")
    public String user(){
        return "user";
    }
}
