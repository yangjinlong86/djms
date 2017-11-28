package org.dj.bms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: VarietyController
 * @author pufangfei@163.com
 * @date 2017年11月27日 下午9:03:46
 */
@RequestMapping("/variety")
@Controller
public class VarietyController extends BaseController {
	@RequestMapping("")
	public String variety() {
		return "variety/variety";
	}
}
