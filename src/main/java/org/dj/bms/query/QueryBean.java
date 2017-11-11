package org.dj.bms.query;

import java.util.Date;

/**
 * @ClassName: QueryBean
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:35:12
 */

public class QueryBean {
	/**
	 * 页数
	 */
	private Integer pageNum;
	/**
	 * 每页条数
	 */
	private Integer limitNum;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 开始日期
	 */
	private Date startDate;
	/**
	 * 结束日期
	 */
	private Date endDate;

    private String corpId;
    private String deptId;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
