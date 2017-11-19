package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Consume;
import org.dj.bms.query.CustQueryInfo;
import org.dj.bms.service.ConsumeService;
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
@RequestMapping("/consume")
public class ConsumeController extends BaseController {
	@Autowired
	@Qualifier("consumeService")
	private ConsumeService consumeService;

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Consume consume) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = consumeService.saveOrUpdate(consume);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/consumeList")
	public ResponseMsg selectconsume(CustQueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(consumeService.select(qb));
		return resData;
	}

	@RequestMapping(path = "delete", method = RequestMethod.POST)
	public ResponseMsg deleteByconsumeId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = consumeService.deleteByIds(ids);
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
