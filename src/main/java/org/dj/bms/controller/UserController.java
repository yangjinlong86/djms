package org.dj.bms.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.User;
import org.dj.bms.query.UserQueryBean;
import org.dj.bms.service.UserService;
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
        // 新建用户时,需要校验用户名是否已经存在
        if(StringUtils.isBlank(user.getId())){
            User existsUser = userService.findByUsername(user.getName());
            if (existsUser != null) {
                return ResponseEnum.ALREADY_EXISTS.getStatus();
            }
        }

		if (userService.saveOrUpdate(user) > 0) {
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
		return userService.selectUsers(queryBean);
	}

	@RequestMapping(value = "/deleteUser/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable String id) {
        if(id.contains(",")){
            String[] idArr = id.split(",");
            userService.deleteByUserIds(idArr);
        }
		if (DBEnum.OPERATION_SUCCESS.getValue() == userService.deleteByUserId(id)) {
			return ResponseEnum.SUCCESS.getStatus();
		}
		return ResponseEnum.FAILED.getStatus();
	}
}
