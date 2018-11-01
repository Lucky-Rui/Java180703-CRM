package com.situ.crm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Permission;
import com.situ.crm.service.IPermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;

	@RequestMapping("/getPermissionPage")
	public String getPermissionPage() {
		return "/permission/permission_list";
	}

	@RequestMapping("/pageList")
	@ResponseBody
	public ServerResponse pageList(Integer page, Integer limit, Permission permission, Date time) {
		System.out.println(time);
		ServerResponse serverResponse = permissionService.pageList(page, limit, permission);
		System.out.println(serverResponse);
		return serverResponse;
	}

	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id) {
		return permissionService.deleteById(id);
	}

	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids) {
		return permissionService.deleteAll(ids);
	}

	@RequestMapping("/getAddPage")
	public String getAddPage() {
		return "/permission/permission_add";
	}

	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Permission permission) {
		return permissionService.add(permission);
	}

	@RequestMapping("/selectAllPermisssions")
	@ResponseBody
	public ServerResponse selectAllPermisssions() {
		return permissionService.selectAllPermisssions();
	}
}
