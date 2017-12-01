package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Order;
import org.dj.bms.query.IQueryInfo;

/**
* @ClassName: OrderService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月19日 下午8:54:09
*/

public interface OrderService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Order order);
	/**
	* @Title: deleteById
	* @Description: 通过主键删除顾客信息
	* @param  id
	* @return boolean    
	* @throws
	 */
	boolean deleteById(String id);
	/**
	 * 
	* @Title: select
	* @Description: TODO
	* @param @param qb
	* @param @return    
	* @return Page<Order>    
	* @throws
	 */
	PageInfo<Order> select(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
}
