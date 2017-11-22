package org.dj.bms.service;

import org.dj.bms.model.StockUse;
import org.dj.bms.query.IQueryInfo;

import com.github.pagehelper.PageInfo;

/**
* @ClassName: StockService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月22日 下午9:32:01
*/

public interface StockUseService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改
	 * @param Stock 库存使用信息
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(StockUse stockUse);
	/**
	 * 
	* @Title: select
	* @param @param qb
	* @param @return    
	* @return Page<StockUse>    
	* @throws
	 */
	PageInfo<StockUse> select(IQueryInfo qb);
	/**
	 * 删除信息
	* @Title: deleteByIds
	* @param @param ids
	* @return boolean    
	* @throws
	 */
	boolean deleteByIds(String ids);
}
