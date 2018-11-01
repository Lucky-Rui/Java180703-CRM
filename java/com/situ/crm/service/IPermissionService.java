package com.situ.crm.service;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Permission;

public interface IPermissionService {
	ServerResponse pageList(Integer page, Integer limit, Permission permission);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Permission permission);

	Permission selectByPrimaryKey(Integer permissionId);

	ServerResponse selectAllPermisssions();
}
