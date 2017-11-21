package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Customer;
import org.dj.bms.query.CustQueryInfo;
import org.dj.bms.service.CustomerService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: CustomerController
 * @Description: 顾客控制层
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:58:45
 */
@Controller
public class CustomerController extends BaseController {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;

	@RequestMapping("/customer")
	public String customer() {
		return "customer/customer";
	}

	@ResponseBody
	@RequestMapping(path = "saveOrUpdateCust", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Customer cust) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = customerService.saveOrUpdate(cust);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@ResponseBody
	@RequestMapping(value = "/custList")
	public ResponseMsg selectCustomer(CustQueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(customerService.selectCustomer(qb));
		return resData;
	}

	@ResponseBody
	@RequestMapping(path = "delete", method = RequestMethod.POST)
	public ResponseMsg deleteByCustomerId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = customerService.deleteByIds(ids);
			} else {
				resData.setStatus(false);
				resData.setMsg("参数为空");
			}
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

}
