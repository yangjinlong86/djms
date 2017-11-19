package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Order;
import org.dj.bms.query.CustQueryInfo;
import org.dj.bms.service.OrderService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FormulaController
 * @Description: 订单、销售记录
 * @author pufangfei@163.com
 * @date 2017年11月19日 下午6:16:39
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {
	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Order order) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = orderService.saveOrUpdate(order);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/orderList")
	public ResponseMsg selectorder(CustQueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(orderService.select(qb));
		return resData;
	}

	@RequestMapping(path = "delete", method = RequestMethod.POST)
	public ResponseMsg deleteByorderId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = orderService.deleteByIds(ids);
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
