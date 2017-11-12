package org.dj.bms.controller;

import org.dj.bms.enumeration.DBEnum;
import org.dj.bms.enumeration.ResponseEnum;
import org.dj.bms.model.User;
import org.dj.bms.query.UserQueryBean;
import org.dj.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

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
	public String save(User user) {
		userService.saveOrUpdate(user);
		return "redirect:/user";
	}

	/**
	 * 分页查询用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList")
	@ResponseBody
	public PageInfo<User> selectAllUsers(UserQueryBean queryBean, Model model) {
		// 总记录数
		queryBean.setCount(userService.selectCountUser());
		model.addAttribute("queryBean", queryBean);
		return userService.selectUsers(queryBean);
	}

	@RequestMapping(value = "/deleteUser/{id}")
	@ResponseBody
	public String deleteUser(@PathVariable String id) {
		if (DBEnum.OPERATION_SUCCESS.getValue() == userService.deleteByUserId(id)) {
			return ResponseEnum.SUCCESS.getStatus();
		}
		return ResponseEnum.FAILED.getStatus();
	}
}
