package org.dj.bms.controller;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jason on 17/10/29.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public String save(@ModelAttribute User user){
        userService.saveOrUpdate(user);
        return "redirect:/user";
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="userlist/{pageNum}/{pageSize}")
    @ResponseBody
    public PageInfo<User> selectAllUsers(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        return userService.selectAllUsers(pageNum, pageSize);
    }
}
