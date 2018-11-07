package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Order;
import com.situ.crm.service.IOrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/getOrderPage")
	public String getOrderPage() {
		return "/order/order_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Order order) {
		ServerResponse serverResponse = orderService.pageList(page, limit, order);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return orderService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return orderService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/order/order_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Order order) {
		return orderService.add(order);
	}

	@RequestMapping("/getUpdatePage")
	public String getUpdatePage() {
		return "/order/order_update";
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Order order) {
		return orderService.update(order);
	}

}