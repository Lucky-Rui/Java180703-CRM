package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.entity.CusDevPlan;

public interface CusDevPlanMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	int insert(CusDevPlan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	int insertSelective(CusDevPlan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	CusDevPlan selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(CusDevPlan record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table cus_dev_plan
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(CusDevPlan record);

	List<CusDevPlan> selectBySaleChanceId(Integer id);

	List<CusDevPlan> pageList(Integer id);

	int deleteAll(String[] idArray);
}