package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Product;

public interface IProductService {
	ServerResponse pageList(Integer page, Integer limit, Product product);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Product product);

	Product selectByPrimaryKey(Integer productId);

	ServerResponse update(Product product);

	List<Product> selectAll();

	Product findById(Integer id);

	ServerResponse selectProductCount();
}
