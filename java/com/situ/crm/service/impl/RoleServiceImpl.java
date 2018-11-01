package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Permission;
import com.situ.crm.entity.Role;
import com.situ.crm.entity.RolePermission;
import com.situ.crm.mapper.PermissionMapper;
import com.situ.crm.mapper.RoleMapper;
import com.situ.crm.mapper.RolePermissionMapper;
import com.situ.crm.service.IRoleService;
import com.situ.crm.vo.LayUISelectMVO;

@Service
public class RoleServiceImpl implements IRoleService {
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, Role role) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<Role> list = roleMapper.pageList(role);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = roleMapper.deleteByPrimaryKey(id);
			if (count == 1) {
				return ServerResponse.createSuccess("删除成功");
			} else {
				return ServerResponse.createError("删除失败");
			}
		} catch (Exception e) {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public ServerResponse deleteAll(String ids) {
		String[] idArray = ids.split(",");
		int count = roleMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public ServerResponse add(Role role, String permissions) {
		try {
			// 将角色插入到数据库中
			int count = roleMapper.insert(role);
			// 将角色-权限多对多关系放到角色-权限role_permission表中
			// 3,4,8
			String[] permissionIds = permissions.split(",");
			for (String permissionId : permissionIds) {
				RolePermission rolePermission = new RolePermission(role.getId(), Integer.parseInt(permissionId));
				rolePermissionMapper.insert(rolePermission);
			}
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public Role selectByPrimaryKey(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public ServerResponse selectRoleAndPermisssions(Integer roleId) {
		Map<String, Object> map = new HashMap<>();
		// 查询role信息放到map中
		Role role = roleMapper.selectByPrimaryKey(roleId);
		map.put("role", role);
		// 查询所有的权限放到map中
		List<Permission> permissions = permissionMapper.pageList(new Permission());
		List<LayUISelectMVO> list = new ArrayList<>();
		for (Permission permission : permissions) {
			LayUISelectMVO layUISelectMVO = new LayUISelectMVO();
			layUISelectMVO.setId(permission.getId());
			layUISelectMVO.setName(permission.getName());
			layUISelectMVO.setStatus(1);
			list.add(layUISelectMVO);
		}
		map.put("allPermissions", list);
		// 这个角色Role对应的所有权限id的数组放到map中
		List<Integer> roleSelectedPermissionIds = rolePermissionMapper.selectPermissionIdsByRoleId(roleId);
		map.put("selectIds", roleSelectedPermissionIds.toArray());

		return ServerResponse.createSuccess("成功", map);
	}

	@Override
	public ServerResponse update(Role role, String permissions) {
		try {
			// 将角色插入到数据库中
			int count = roleMapper.updateByPrimaryKey(role);
			// 删除这个角色下面原来的权限
			rolePermissionMapper.deleteByRoleId(role.getId());
			// 将角色-权限多对多关系放到角色-权限表中
			String[] permissionIds = permissions.split(",");
			for (String permissionId : permissionIds) {
				RolePermission rolePermission = new RolePermission(role.getId(), Integer.parseInt(permissionId));
				rolePermissionMapper.insert(rolePermission);
			}
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public ServerResponse selectAllRoles() {
		List<Role> roles = roleMapper.pageList(new Role());
		List<LayUISelectMVO> list = new ArrayList<>();
		for (Role role : roles) {
			LayUISelectMVO layUISelectMVO = new LayUISelectMVO();
			layUISelectMVO.setId(role.getId());
			layUISelectMVO.setName(role.getSn());
			layUISelectMVO.setStatus(1);
			list.add(layUISelectMVO);
		}

		return ServerResponse.createSuccess("查找成功", list);
	}
}
