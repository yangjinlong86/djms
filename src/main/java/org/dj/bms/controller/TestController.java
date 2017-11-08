package org.dj.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Created by jason on 17/11/7.
 */
@Controller
public class TestController {
    @RequestMapping("/validate")
    public String validate(){
        return "validate";
    }
}
