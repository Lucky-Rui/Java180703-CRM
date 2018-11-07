package com.situ.crm.service;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Order;

public interface IOrderService {
	ServerResponse pageList(Integer page, Integer limit, Order order);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Order order);

	ServerResponse update(Order order);

}
