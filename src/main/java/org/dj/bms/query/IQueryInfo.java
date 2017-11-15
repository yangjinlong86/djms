package org.dj.bms.query;

/**
 * @ClassName: IQueryInfo
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月14日 下午10:53:44
 */

public interface IQueryInfo {
	public int getPageNum();

	public int getPageSize();

	public String getOrderBy();
}
