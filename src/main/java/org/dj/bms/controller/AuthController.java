package org.dj.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jason
 * @date 17/11/3
 */
@Controller
public class AuthController {

	@RequestMapping("/resource")
	public String resource() {
		return "authorize/resource";
	}

	@RequestMapping("/role")
	public String role() {
		return "authorize/role";
	}

	@RequestMapping("/user")
	public String user() {
		return "authorize/user";
	}

	@RequestMapping("/customer")
	public String customer() {
		return "customer/customer";
	}
}
