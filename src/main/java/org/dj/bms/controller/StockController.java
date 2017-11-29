package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Stock;
import org.dj.bms.query.QueryInfo;
import org.dj.bms.service.StockService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: StockController
 * @author pufangfei@163.com
 * @date 2017年11月22日 下午9:31:28
 */
@Controller
@RequestMapping("/stock")
public class StockController extends BaseController {
	@Autowired
	private StockService stockService;

	@RequestMapping("")
	public String stock() {
		return "stock/stock";
	}

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Stock stock) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isBlank(stock.getCorpId())) {
				stock.setCorpId(getCurrentUser().getCorpId());
			}
			status = stockService.saveOrUpdate(stock);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/stockList")
	@ResponseBody
	public ResponseMsg selectProduct(QueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(stockService.select(qb));
		return resData;
	}

	@RequestMapping(path = "deleteByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg deleteByProductId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = stockService.deleteByIds(ids);
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
