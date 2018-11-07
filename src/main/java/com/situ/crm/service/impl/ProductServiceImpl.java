package com.situ.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Product;
import com.situ.crm.mapper.ProductMapper;
import com.situ.crm.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, Product product) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<Product> list = productMapper.pageList(product);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = productMapper.deleteByPrimaryKey(id);
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
		int count = productMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public Product selectByPrimaryKey(Integer productId) {
		return productMapper.selectByPrimaryKey(productId);
	}

	@Override
	public ServerResponse add(Product product) {
		try {
			// 将角色插入到数据库中
			int count = productMapper.insert(product);
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse update(Product product) {
		try {
			// 将角色插入到数据库中
			int count = productMapper.updateByPrimaryKey(product);
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public List<Product> selectAll() {
		return productMapper.pageList(new Product());
	}

	@Override
	public Product findById(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public ServerResponse selectProductCount() {
		List<Product> list = productMapper.selectProductCount();
		return ServerResponse.createSuccess("查找成功", list);
	}
}
