package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Variety;
import org.dj.bms.query.IQueryInfo;

/**
* @ClassName: VarietyService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月16日 下午10:40:32
*/

public interface VarietyService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改
	 * @param variety 用品
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Variety variety);
	/**
	* @Title: deleteById
	* @Description: 通过主键删除
	* @param  id
	* @return boolean    
	* @throws
	 */
	boolean deleteById(String id);
	/**
	 * 
	* @Title: selectCustomer
	* @param @param qb
	* @param @return    
	* @return Page<Product>    
	* @throws
	 */
	PageInfo<Variety> select(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
}
