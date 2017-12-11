package org.dj.bms.service;

import com.github.pagehelper.PageInfo;
import org.dj.bms.model.Consume;
import org.dj.bms.query.IQueryInfo;

/**
* @ClassName: ConsumeService
* @Description: TODO
* @author pufangfei@163.com
* @date 2017年11月19日 下午8:46:59
*/

public interface ConsumeService {
	/**
	 * 
	 * @Title: saveOrUpdate
	 * @return boolean 
	 * @throws
	 */
	boolean saveOrUpdate(Consume consume);
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
	* @return Page<Consume>    
	* @throws
	 */
	PageInfo<Consume> select(IQueryInfo qb);

    PageInfo<Consume> selectConsumptions(IQueryInfo qb);

    /**
     * deleteByIds
     *
     * @param ids
     * @return
     */
    boolean deleteByIds(String ids);
}
