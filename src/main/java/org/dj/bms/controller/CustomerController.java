package org.dj.bms.controller;

import org.dj.bms.model.Customer;
import org.dj.bms.service.CustomerService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CustomerController
 * @Description: 顾客控制层
 * @author pufangfei@163.com
 * @date 2017年11月11日 上午9:58:45
 */
@RestController
public class CustomerController extends BaseController {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;

	@RequestMapping(path = "saveOrUpdateCust", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Customer cust) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = customerService.saveOrUpdate(cust);
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(path = "delete/{id}", method = RequestMethod.GET)
	public ResponseMsg deleteByCustomerId(@PathVariable("id") String id) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = customerService.deleteByCustomerId(id);
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

}
