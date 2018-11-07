package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.CusDevPlan;
import com.situ.crm.service.ICusDevPlanService;
import com.situ.crm.service.ISaleChanceService;

@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {

	@Autowired
	private ICusDevPlanService cusDevPlanService;
	@Autowired
	private ISaleChanceService saleChanceService;

	@RequestMapping(value = "/getCusDevPlanPage")
	public String getCusDevPlanPage(Integer id, Model model) {
		model.addAttribute("id", id);
		return "/cus_dev_plan/cus_dev_plan_list";
	}

	@RequestMapping(value = "/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Integer id) {
		ServerResponse serverResponse = cusDevPlanService.pageList(page, limit, id);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return cusDevPlanService.deleteById(id);
	}

	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return cusDevPlanService.deleteAll(ids);
	}

	@RequestMapping(value = "/getAddPage")
	public String getAddPage(Integer id, Model model) {
		model.addAttribute("id", id);
		return "/cus_dev_plan/cus_dev_plan_add";
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ServerResponse add(CusDevPlan cusDevPlan, Integer saleChanceId) {
		saleChanceService.updateDevResult(saleChanceId);
		return cusDevPlanService.add(cusDevPlan);
	}

	@RequestMapping(value = "/getUpdatePage")
	public String getUpdatePage() {
		return "/cusDevPlan/cusDevPlan_update";
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ServerResponse update(CusDevPlan cusDevPlan) {
		return cusDevPlanService.update(cusDevPlan);
	}

	@RequestMapping(value = "/selectBySaleChanceId")
	@ResponseBody
	public List<CusDevPlan> selectBySaleChanceId(Integer id) {
		List<CusDevPlan> serverResponse = cusDevPlanService.selectBySaleChanceId(id);
		System.out.println(serverResponse);
		return serverResponse;
	}

}