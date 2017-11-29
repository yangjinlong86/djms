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
	// 用户ID 默认当前登录用的id
	private String userId;
	// 名称
	private String name;

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
			this.userId = user.getId();
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
