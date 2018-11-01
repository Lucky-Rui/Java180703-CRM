package com.situ.crm.service;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.SaleChance;

public interface ISaleChanceService {
	ServerResponse pageList(Integer page, Integer limit, SaleChance saleChance);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(SaleChance saleChance);

	SaleChance selectByPrimaryKey(Integer saleChanceId);

	ServerResponse update(SaleChance saleChance);
}
