package org.dj.bms.controller;

import org.apache.commons.lang3.StringUtils;
import org.dj.bms.model.Product;
import org.dj.bms.query.CustQueryInfo;
import org.dj.bms.service.ProductService;
import org.dj.bms.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ProductController
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月16日 下午10:36:36
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {
	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	@RequestMapping(path = "saveOrUpdate", method = RequestMethod.POST)
	public ResponseMsg saveOrUpdateCust(@ModelAttribute Product product) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			status = productService.saveOrUpdate(product);
			resData.setMsg("操作成功");
		} catch (Exception e) {
			logger.error(e + "");
			resData.setMsg(e.getMessage());
		}
		resData.setStatus(status);
		return resData;
	}

	@RequestMapping(value = "/productList")
	public ResponseMsg selectProduct(CustQueryInfo qb) {
		ResponseMsg resData = getRes(true);
		resData.setData(productService.selectProduct(qb));
		return resData;
	}

	@RequestMapping(path = "deleteByIds", method = RequestMethod.POST)
	public ResponseMsg deleteByProductId(String ids) {
		boolean status = false;
		ResponseMsg resData = getRes(status);
		try {
			if (StringUtils.isNotBlank(ids)) {
				status = productService.deleteByIds(ids);
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
