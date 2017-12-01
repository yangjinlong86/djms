package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Stock;
import org.dj.bms.query.IQueryInfo;

/**
* @ClassName: StockService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月22日 下午9:32:01
*/

public interface StockService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改
	 * @param Stock 库存信息
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Stock stock);
	/**
	 * 
	* @Title: selectCustomer
	* @param @param qb
	* @param @return    
	* @return Page<Stock>    
	* @throws
	 */
	PageInfo<Stock> select(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
	
}
