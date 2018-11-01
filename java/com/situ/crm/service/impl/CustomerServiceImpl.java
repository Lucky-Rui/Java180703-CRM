package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Customer;
import com.situ.crm.mapper.CustomerMapper;
import com.situ.crm.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, Customer customer) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<Customer> list = customerMapper.pageList(customer);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = customerMapper.deleteByPrimaryKey(id);
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
		int count = customerMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public Customer selectByPrimaryKey(Integer customerId) {
		return customerMapper.selectByPrimaryKey(customerId);
	}

	@Override
	public ServerResponse add(Customer customer) {
		try {
			// 将角色插入到数据库中
			int count = customerMapper.insert(customer);
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse update(Customer customer) {
		try {
			// 将角色插入到数据库中
			int count = customerMapper.updateByPrimaryKey(customer);
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public List<Customer> selectAll() {
		return customerMapper.pageList(new Customer());
	}
}
