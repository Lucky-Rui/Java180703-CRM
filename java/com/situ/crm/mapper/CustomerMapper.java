package com.situ.crm.mapper;

import java.util.List;

import com.situ.crm.entity.Customer;

public interface CustomerMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	int insert(Customer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	int insertSelective(Customer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	Customer selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective(Customer record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table customer
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey(Customer record);

	List<Customer> pageList(Customer customer);

	int deleteAll(String[] idArray);
}