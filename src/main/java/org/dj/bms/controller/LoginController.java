package org.dj.bms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.dj.bms.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by jason on 17/11/2.
 */
@Controller
public class LoginController extends BaseController {

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, User user, Model model) {
		if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
			return "login";
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(token);
            model.addAttribute("user",user);
			return "redirect:/index";
		} catch (LockedAccountException lae) {
			token.clear();
			request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
			return "login";
		} catch (AuthenticationException e) {
			token.clear();
			request.setAttribute("msg", "用户或密码不正确！");
			return "login";
		}
	}

}
