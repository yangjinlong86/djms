package org.dj.bms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Bms
 * @Description: TODO
 * @author pufangfei@163.com
 * @date 2017年11月8日 下午11:21:00
 */

public class Bms implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = 2025675011981169303L;
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 创建用户
	 */
	private String createUserId;
	/**
	 * 更新用户
	 */
	private String updateUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
