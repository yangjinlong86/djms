package org.dj.bms.controller;

import com.github.pagehelper.PageInfo;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.User;
import org.dj.bms.query.UserQueryBean;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * UserContrller
 * @author Created by jason on 17/10/29.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     * @param user
     * @return
     */
    @PostMapping(value="/saveUser")
    public String save(@ModelAttribute User user){
        userService.saveOrUpdate(user);
        return "redirect:/user";
    }

    /**
     * 分页查询用户
     * @return
     */
    @RequestMapping(value="/userList")
    @ResponseBody
    public PageInfo<User> selectAllUsers(UserQueryBean queryBean, Model model){
        model.addAttribute("queryBean",queryBean);
        return userService.selectAllUsers(queryBean.getPageNum(), queryBean.getLimitNum());
    }

    @RequestMapping(value="/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable String id){
        if(DBEnum.OPERATION_SUCCESS.getValue() == userService.deleteByUserId(id)){
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }
}
