package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Product;
import com.situ.crm.service.IProductService;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@RequestMapping("/getProductPage")
	public String getPermissionPage() {
		return "/product/product_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Product product) {
		ServerResponse serverResponse = productService.pageList(page, limit, product);
		return serverResponse;
	}
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return productService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return productService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/product/product_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Product product) {
		return productService.add(product);
	}
	
	@RequestMapping(value="/toUpdate")
	public String toUpdate(Integer id,Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "/product/product_update";
	}

	@RequestMapping(value="/update")
	@ResponseBody
	public ServerResponse update(Product product) {
		return productService.update(product);
	}
	
	/*ECharts统计商品数量*/
    @RequestMapping(value="/getProductCountPage")
    public String getProductCountPage() {
		return "/product/product_count";
	}
    
    @RequestMapping(value="/productCount")
    @ResponseBody
    public ServerResponse productCount() {
		return productService.selectProductCount();
	}
	
}