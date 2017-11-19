package org.dj.bms.service;

import org.dj.bms.model.FormulaDetails;
import org.dj.bms.query.IQueryInfo;

import com.github.pagehelper.PageInfo;

/**
* @ClassName: FormulaDetailsService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月19日 下午8:46:59
*/

public interface FormulaDetailsService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @Description: 更新或修改顾客基本信息
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(FormulaDetails formulaDetails);
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
	* @return Page<FormulaDetails>    
	* @throws
	 */
	PageInfo<FormulaDetails> select(IQueryInfo qb);
	
	boolean deleteByIds(String ids);
}
