package com.situ.crm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Customer;
import com.situ.crm.entity.Product;
import com.situ.crm.entity.SaleChance;
import com.situ.crm.entity.User;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.service.IProductService;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.service.IUserService;

@RequestMapping("/saleChance")
@Controller
public class SaleChanceController {
	@Autowired
	private ISaleChanceService saleChanceService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/getSaleChancePage")
	public String getSaleChancePage() {
		return "/sale_chance/sale_chance_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, SaleChance saleChance, Date time) {
		System.out.println(time);
		ServerResponse serverResponse = saleChanceService.pageList(page, limit, saleChance);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return saleChanceService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return saleChanceService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage(Model model) {
		List<Customer> customerList = customerService.selectAll();
		model.addAttribute("customerList", customerList);
		List<Product> productList = productService.selectAll();
		model.addAttribute("productList", productList);
		List<User> userList = userService.selectXiaoShouUser();
		model.addAttribute("userList", userList);
		return "/sale_chance/sale_chance_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(SaleChance saleChance) {
		System.out.println(saleChance);
		return saleChanceService.add(saleChance);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(SaleChance saleChance) {
		System.out.println(saleChance);
		return saleChanceService.update(saleChance);
	}

	@RequestMapping("/getUpdatePage")
	public String getUpdatePage() {
		return "/saleChance/saleChance_update";
	}

}
