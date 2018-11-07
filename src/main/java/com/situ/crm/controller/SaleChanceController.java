package com.situ.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Customer;
import com.situ.crm.entity.Order;
import com.situ.crm.entity.Product;
import com.situ.crm.entity.SaleChance;
import com.situ.crm.entity.User;
import com.situ.crm.service.ICusDevPlanService;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.service.IOrderService;
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
	@Autowired
	private ICusDevPlanService cusDevPlanService;
	@Autowired
	private IOrderService orderService;

	@RequestMapping("/getSaleChancePage")
	public String getSaleChancePage(Model model) {
		List<Customer> customerList = customerService.selectAll();
		model.addAttribute("customerList", customerList);
		return "/sale_chance/sale_chance_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, SaleChance saleChance, Date time) {
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

	@RequestMapping(value = "/selectById")
	@ResponseBody
	public ServerResponse selectById(Integer id) {
		// 根据id查出saleChance表的信息
		SaleChance saleChance = saleChanceService.selectById(id);
		System.out.println(saleChance);
		// 填充order表
		Order order = new Order();
		order.setCustomerId(saleChance.getCustomerId()); // 客户id

		// 生成订单
		Date date = new Date();
		SimpleDateFormat toOrderNo = new SimpleDateFormat("yyyyMMddhhmmss");
		String orderNo = toOrderNo.format(date);
		order.setOrderNo(orderNo); // 订单号
		order.setSaleChanceId(saleChance.getId()); // 营销机会id
		order.setOrderDate(date); // 订购日期
		order.setProductId(saleChance.getProductId()); // 商品id
		order.setStatus(1); // 订单状态 默认1：已回款

		// 添加订单后修改开发状态为开发成功
		saleChance.setDevResult(2); // 2：开发成功
		saleChanceService.update(saleChance);

		return orderService.add(order);
	}

	@RequestMapping(value = "/loser")
	@ResponseBody
	public ServerResponse loser(Integer id) {
		// 查出saleChance表的信息
		SaleChance saleChance = saleChanceService.selectById(id);
		saleChance.setDevResult(3); // 3：开发失败
		return saleChanceService.update(saleChance);
	}
}
