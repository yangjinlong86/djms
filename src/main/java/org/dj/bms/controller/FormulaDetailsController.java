package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.FormulaDetails;
import org.dj.bms.query.CustQueryInfo;
import org.dj.bms.service.FormulaDetailsService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FormulaController
 * @Description: 美容方案
 * @author pufangfei@163.com
 * @date 2017年11月19日 下午6:16:39
 */
@RestController
@RequestMapping("/formulaDetails")
public class FormulaDetailsController extends BaseController {
	@Autowired
	@Qualifier("formulaDetailsService")
	private FormulaDetailsService formulaDetailsService;

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute FormulaDetails formulaDetails) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = formulaDetailsService.saveOrUpdate(formulaDetails);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/formulaDetailsList")
	public ResponseMsg selectFormula(CustQueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(formulaDetailsService.select(qb));
		return resData;
	}

	@RequestMapping(path = "delete", method = RequestMethod.POST)
	public ResponseMsg deleteByFormulaId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = formulaDetailsService.deleteByIds(ids);
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
