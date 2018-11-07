package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Customer;
import com.situ.crm.service.ICustomerService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@RequestMapping(value = "/getCustomerPage")
	public String getCustomerPage() {
		return "/customer/customer_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Customer customer) {
		ServerResponse serverResponse = customerService.pageList(page, limit, customer);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return customerService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return customerService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/customer/customer_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Customer customer) {
		return customerService.add(customer);
	}

	@RequestMapping("/getUpdatePage")
	public String getUpdatePage() {
		return "/customer/customer_update";
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Customer customer) {
		return customerService.update(customer);
	}

}
