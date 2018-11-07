package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.CusDevPlan;
import com.situ.crm.mapper.CusDevPlanMapper;
import com.situ.crm.service.ICusDevPlanService;

@Service
public class CusDevPlanServiceImpl implements ICusDevPlanService {

	@Autowired
	private CusDevPlanMapper cusDevPlanMapper;

	@Override
	public List<CusDevPlan> selectBySaleChanceId(Integer id) {
		return cusDevPlanMapper.selectBySaleChanceId(id);
	}

	@Override
	public ServerResponse pageList(Integer page, Integer limit, Integer id) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<CusDevPlan> list = cusDevPlanMapper.pageList(id);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = cusDevPlanMapper.deleteByPrimaryKey(id);
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
		int count = cusDevPlanMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public CusDevPlan selectByPrimaryKey(Integer cusDevPlanId) {
		return cusDevPlanMapper.selectByPrimaryKey(cusDevPlanId);
	}

	@Override
	public ServerResponse add(CusDevPlan cusDevPlan) {
		try {
			// 将角色插入到数据库中
			int count = cusDevPlanMapper.insert(cusDevPlan);
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse update(CusDevPlan cusDevPlan) {
		try {
			// 将角色插入到数据库中
			int count = cusDevPlanMapper.updateByPrimaryKey(cusDevPlan);
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

}
