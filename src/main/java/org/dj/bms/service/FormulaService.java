package org.dj.bms.service;

import org.dj.bms.model.Formula;
import org.dj.bms.query.IQueryInfo;

import com.github.pagehelper.PageInfo;

/**
* @ClassName: FormulaService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月19日 下午8:30:49
*/

public interface FormulaService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改顾客基本信息
	 * @param Formula 顾客实体
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Formula formula);
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
	* @return Page<Formula>    
	* @throws
	 */
	PageInfo<Formula> select(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
}
