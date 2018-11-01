package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Permission;
import com.situ.crm.mapper.PermissionMapper;
import com.situ.crm.service.IPermissionService;
import com.situ.crm.vo.LayUISelectMVO;

@Service
public class PermissionServiceImpl implements IPermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, Permission permission) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<Permission> list = permissionMapper.pageList(permission);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = permissionMapper.deleteByPrimaryKey(id);
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
		int count = permissionMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public ServerResponse add(Permission permission) {
		try {
			int count = permissionMapper.insert(permission);
			if (count == 1) {
				return ServerResponse.createSuccess("添加成功");
			} else {
				return ServerResponse.createError("添加失败");
			}
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public Permission selectByPrimaryKey(Integer permissionId) {
		return permissionMapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public ServerResponse selectAllPermisssions() {
		// pageList没有传递任何参数，相当于查找所有
		List<Permission> permissions = permissionMapper.pageList(new Permission());
		// SelectM需要的结构{"id":12,"name":"长者","status":0}，
		// 所以在这里转换一下
		List<LayUISelectMVO> list = new ArrayList<>();
		for (Permission permission : permissions) {
			LayUISelectMVO layUISelectMVO = new LayUISelectMVO();
			layUISelectMVO.setId(permission.getId());
			layUISelectMVO.setName(permission.getName());
			layUISelectMVO.setStatus(1);
			list.add(layUISelectMVO);
		}

		return ServerResponse.createSuccess("查找成功", list);
	}
}
