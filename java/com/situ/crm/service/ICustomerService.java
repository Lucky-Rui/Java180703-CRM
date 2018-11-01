package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Customer;

public interface ICustomerService {

	ServerResponse pageList(Integer page, Integer limit, Customer customer);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Customer customer);

	Customer selectByPrimaryKey(Integer customerId);

	ServerResponse update(Customer customer);

	List<Customer> selectAll();
}
