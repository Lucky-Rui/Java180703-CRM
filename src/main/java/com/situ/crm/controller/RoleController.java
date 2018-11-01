package com.situ.crm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Role;
import com.situ.crm.service.IRoleService;

@RequestMapping("/role")
@Controller
public class RoleController {
	@Autowired
	private IRoleService roleService;

	@RequestMapping("/getRolePage")
	public String getRolePage() {
		return "/role/role_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Role role, Date time) {
		System.out.println(time);
		ServerResponse serverResponse = roleService.pageList(page, limit, role);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return roleService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return roleService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/role/role_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Role role, String permissions) {
		System.out.println(role);
		System.out.println(permissions);
		return roleService.add(role, permissions);
	}

	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Role role, String permissions) {
		System.out.println(role);
		System.out.println(permissions);
		return roleService.update(role, permissions);
	}

	@RequestMapping("/getUpdatePage")
	public String getUpdatePage() {
		return "/role/role_update";
	}

	@RequestMapping("/selectRoleAndPermisssions")
	@ResponseBody
	public ServerResponse selectRoleAndPermisssions(Integer roleId) {
		return roleService.selectRoleAndPermisssions(roleId);
	}

	@RequestMapping("/selectAllRoles")
	@ResponseBody
	public ServerResponse selectAllRoles() {
		return roleService.selectAllRoles();
	}
}
