package org.dj.bms.controller;

import com.github.pagehelper.PageInfo;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.User;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        logger.debug("==========saveuser=======");
        return "redirect:/user";
    }

    /**
     * 分页查询用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value="/userList/{pageNum}/{pageSize}")
    @ResponseBody
    public PageInfo<User> selectAllUsers(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        return userService.selectAllUsers(pageNum, pageSize);
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
