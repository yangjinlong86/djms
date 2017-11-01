package org.dj.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jason on 17/10/30.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}