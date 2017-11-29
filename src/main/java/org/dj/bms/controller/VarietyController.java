package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Variety;
import org.dj.bms.query.QueryInfo;
import org.dj.bms.service.VarietyService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: VarietyController
 * @author pufangfei@163.com
 * @date 2017年11月27日 下午9:03:46
 */
@RequestMapping("/variety")
@Controller
public class VarietyController extends BaseController {
	@Autowired
	private VarietyService varietyService;

	@RequestMapping("")
	public String variety() {
		return "variety/variety";
	}

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Variety variety) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isBlank(variety.getCorpId())) {
				variety.setCorpId(getCurrentUser().getCorpId());
			}
			status = varietyService.saveOrUpdate(variety);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/varietyList")
	@ResponseBody
	public ResponseMsg selectProduct(QueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(varietyService.select(qb));
		return resData;
	}

	@RequestMapping(path = "deleteByIds", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg deleteByProductId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = varietyService.deleteByIds(ids);
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
