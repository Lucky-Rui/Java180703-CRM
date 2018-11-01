package com.situ.crm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.User;
import com.situ.crm.service.IUserService;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping("/getUserPage")
	public String getUserPage() {
		return "/user/user_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, User user, Date time) {
		System.out.println(time);
		ServerResponse serverResponse = userService.pageList(page, limit, user);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return userService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return userService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/user/user_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(User user, String roles) {
		System.out.println(user);
		System.out.println(roles);
		return userService.add(user, roles);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(User user, String roles) {
		System.out.println(user);
		System.out.println(roles);
		return userService.update(user, roles);
	}

	@RequestMapping("/selectUserAndRoles")
	@ResponseBody
	public ServerResponse selectUserAndRoles(Integer userId) {
		return userService.selectUserAndRoles(userId);
	}

	@RequestMapping("/getUpdatePage")
	public String getUpdatePage() {
		return "/user/user_update";
	}

	@RequestMapping("/login")
	@ResponseBody
	public ServerResponse login(String name, String password, HttpSession session) {
		return userService.login(name, password, session);
	}

	@RequestMapping("/getLoginPage")
	public String getLoginPage() {
		return "/user/login";
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) {
		userService.exportExcel(response);
	}

}
