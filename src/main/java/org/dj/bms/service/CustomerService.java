package org.dj.bms.service;

import org.dj.bms.model.Customer;

/**
 * @ClassName: CustomerService
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:19:39
 */
public interface CustomerService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改顾客基本信息
	 * @param customer 顾客实体
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Customer customer);
	/**
	* @Title: deleteByCustomerId
	* @Description: 通过主键删除顾客信息
	* @param  id
	* @param     
	* @return boolean    
	* @throws
	 */
	boolean deleteByCustomerId(String id);
}
