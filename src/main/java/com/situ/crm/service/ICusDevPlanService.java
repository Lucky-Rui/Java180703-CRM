package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.CusDevPlan;

public interface ICusDevPlanService {

	List<CusDevPlan> selectBySaleChanceId(Integer id);

	ServerResponse pageList(Integer id, Integer limit, Integer id2);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(CusDevPlan cusDevPlan);

	ServerResponse update(CusDevPlan cusDevPlan);

	CusDevPlan selectByPrimaryKey(Integer cusDevPlanId);

}
