package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Product;
import org.dj.bms.query.IQueryInfo;

/**
* @ClassName: ProductService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月16日 下午10:40:32
*/

public interface ProductService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改
	 * @param customer 顾客实体
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Product product);
	/**
	* @Title: deleteByCustomerId
	* @Description: 通过主键删除
	* @param  id
	* @param     
	* @return boolean    
	* @throws
	 */
	boolean deleteByProductId(String id);
	/**
	 * 
	* @Title: selectCustomer
	* @param @param qb
	* @param @return    
	* @return Page<Product>    
	* @throws
	 */
	PageInfo<Product> selectProduct(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
}
