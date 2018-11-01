package com.situ.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.User;

public interface IUserService {
	ServerResponse pageList(Integer page, Integer limit, User user);

	ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(User user, String roles);

	User selectByPrimaryKey(Integer userId);

	ServerResponse selectUserAndRoles(Integer userId);

	ServerResponse update(User user, String roles);

	ServerResponse login(String name, String password, HttpSession session);

	List<User> selectXiaoShouUser();

	void exportExcel(HttpServletResponse response);
}
