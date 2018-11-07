package com.situ.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.SaleChance;
import com.situ.crm.entity.User;
import com.situ.crm.mapper.SaleChanceMapper;
import com.situ.crm.mapper.UserRoleMapper;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.util.UserContext;

@Service
public class SaleChanceServiceImpl implements ISaleChanceService {
	@Autowired
	private SaleChanceMapper saleChanceMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, SaleChance saleChance) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		// 如果是销售人员，就只能查看自己的营销记录
		List<SaleChance> list = new ArrayList<>();
		User user = (User) UserContext.session.getAttribute(UserContext.USER_IN_SESSION);
		Integer xiaoShouRoleCount = userRoleMapper.selectXiaoShouRoleCountByUserId(user.getId());
		// 如果用户是销售角色，就只能查看自己下面的营销机会
		if (xiaoShouRoleCount > 0) {
			list = saleChanceMapper.selectByUserId(user.getId());
		} else {// 默认查看所有
			list = saleChanceMapper.pageList(saleChance);
		}
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = saleChanceMapper.deleteByPrimaryKey(id);
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
		int count = saleChanceMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public SaleChance selectByPrimaryKey(Integer saleChanceId) {
		return saleChanceMapper.selectByPrimaryKey(saleChanceId);
	}

	@Override
	public ServerResponse add(SaleChance saleChance) {
		try {
			// 根据userId是否分配判断status状态是未分配还是已分配
			// 分配状态 0 未分配 1 已分配
			if (saleChance.getUserId() != null) {
				saleChance.setStatus(1);
			} else {
				saleChance.setStatus(0);
			}
			// 客户开发状态 0 未开发 1 开发中 2 开发成功 3 开发失败
			saleChance.setDevResult(0);
			// 将角色插入到数据库中
			int count = saleChanceMapper.insert(saleChance);
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse update(SaleChance saleChance) {
		try {
			// 将角色插入到数据库中
			int count = saleChanceMapper.updateByPrimaryKey(saleChance);
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public void updateDevResult(Integer saleChanceId) {
		saleChanceMapper.updateDevResult(saleChanceId);		
	}

	@Override
	public SaleChance selectById(Integer id) {
		return saleChanceMapper.selectByPrimaryKey(id);
	}

}
