package org.dj.bms.query;

import org.dj.bms.model.User;
import org.dj.bms.utils.UserUtils;

/**
 * @ClassName: QueryInfo
 * @author pufangfei@163.com
 * @date 2017年11月14日 下午10:25:26
 */

public class QueryInfo implements IQueryInfo {
	// 当前页 默认第一页
	private int pageNum = 1;
	// 每页的数量 默认每页10条数据
	private int pageSize = 2;
	// 排序
	private String orderBy;
	// 单位ID 默认当前登录用户的单位
	private String corpId;
	// 部门ID 默认当前登录用的部门
	private String deptId;

	/**
	 * QueryInfo
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param corpId
	 * @param deptId
	 */

	public QueryInfo() {
		User user = UserUtils.getCurrentUser();
		if (user != null) {
			this.corpId = user.getCorpId();
			this.deptId = user.getDeptId();
		}
	}

	@Override
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

}
