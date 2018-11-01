package com.situ.crm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.situ.crm.entity.User;
import com.situ.crm.util.UserContext;

/**
 * 登陆拦截器
 * 
 * @author WANGRUI
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object target) throws Exception {
		User user = (User) request.getSession().getAttribute(UserContext.USER_IN_SESSION);
		if (user != null) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/user/getLoginPage.action");
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}