package com.situ.crm.service;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Role;

public interface IRoleService {
	ServerResponse pageList(Integer page, Integer limit, Role role);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Role role, String permissions);

	Role selectByPrimaryKey(Integer roleId);

	ServerResponse selectRoleAndPermisssions(Integer roleId);

	ServerResponse update(Role role, String permissions);

	ServerResponse selectAllRoles();
}
