package org.nocoder.djms.query;

/**
 * @author pufangfei@163.com
 * @ClassName: IQueryInfo
 * @Description: TODO
 * @date 2017年11月14日 下午10:53:44
 */

public interface IQueryInfo {
    int getPageNum();

    int getPageSize();

    String getOrderBy();
}
