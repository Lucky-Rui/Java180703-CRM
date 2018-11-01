package com.situ.crm.entity;

import java.util.Date;

public class RolePermission extends RolePermissionKey {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column role_permission.create_time
	 *
	 * @mbggenerated
	 */
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column role_permission.update_time
	 *
	 * @mbggenerated
	 */
	private Date updateTime;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table role_permission
	 *
	 * @mbggenerated
	 */
	public RolePermission(Integer roleId, Integer permissionId, Date createTime, Date updateTime) {
		super(roleId, permissionId);
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table role_permission
	 *
	 * @mbggenerated
	 */
	public RolePermission() {
		super();
	}

	public RolePermission(Integer roleId, int permissionId) {
		super(roleId, permissionId);
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column role_permission.create_time
	 *
	 * @return the value of role_permission.create_time
	 *
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column role_permission.create_time
	 *
	 * @param createTime
	 *            the value for role_permission.create_time
	 *
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column role_permission.update_time
	 *
	 * @return the value of role_permission.update_time
	 *
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column role_permission.update_time
	 *
	 * @param updateTime
	 *            the value for role_permission.update_time
	 *
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table role_permission
	 *
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", createTime=").append(createTime);
		sb.append(", updateTime=").append(updateTime);
		sb.append("]");
		return sb.toString();
	}
}