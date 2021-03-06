package org.nocoder.djms.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.nocoder.djms.enumeration.DBEnum;
import org.nocoder.djms.enumeration.OrganizationEnum;
import org.nocoder.djms.enumeration.ResponseEnum;
import org.nocoder.djms.model.User;
import org.nocoder.djms.query.UserQueryBean;
import org.nocoder.djms.service.UserService;
import org.nocoder.djms.utils.EncryptUtil;
import org.nocoder.djms.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserContrller
 *
 * @author Created by jason on 17/10/29.
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveUser")
    @ResponseBody
    public String save(User user) {
        if (userService.countByUsername(user) >= 1) {
            return ResponseEnum.ALREADY_EXISTS.getStatus();
        }
        if (userService.saveOrUpdate(user) > DBEnum.OPERATION_FAILED.getValue()) {
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }

    @RequestMapping(value = "/saveUserRole")
    @ResponseBody
    public String saveUserRole(User user) {
        if (userService.saveUserRole(user) > DBEnum.OPERATION_FAILED.getValue()) {
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }

    /**
     * 分页查询用户
     *
     * @return
     */
    @RequestMapping(value = "/userList")
    @ResponseBody
    public PageInfo<User> selectAllUsers(UserQueryBean queryBean) {
        if (!OrganizationEnum.CORP.val().equals(UserUtils.getCurrentUser().getCorpId())) {
            queryBean.setCorpId(UserUtils.getCurrentUser().getCorpId());
        }
        return userService.selectUsers(queryBean);
    }

    /**
     * 单个,批量删除用户
     * 批量删除Id时,参数ID 是 多个id用逗号隔开的字符串
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable String id) {
        // id为空或者id包含当前用户,返回删除失败
        if (StringUtils.isBlank(id) || id.contains(UserUtils.getCurrentUser().getId())) {
            return ResponseEnum.FAILED.getStatus();
        }
        // 批量删除
        String separator = ",";
        if (id.contains(separator)) {
            String[] idArr = id.split(",");
            // 拆分id为字符串数组作为参数传入
            if (DBEnum.OPERATION_SUCCESS.getValue() <= userService.deleteByUserIds(idArr)) {
                return ResponseEnum.SUCCESS.getStatus();
            }
        }
        // 单个删除
        if (DBEnum.OPERATION_SUCCESS.getValue() <= userService.deleteByUserId(id)) {
            return ResponseEnum.SUCCESS.getStatus();
        }
        return ResponseEnum.FAILED.getStatus();
    }

    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public boolean updatePassword(User user) {
        if (user == null) {
            return false;
        }
        user.setId(UserUtils.getCurrentUser().getId());
        user.setName(UserUtils.getCurrentUser().getName());
        if (userService.validatePassword(user)) {
            user.setPassword(user.getNewPassword());
            EncryptUtil.encryptPassword(user);
            return userService.saveOrUpdate(user) > 0;
        }
        return false;
    }

}
