package com.xms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xms.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response,Object handler) throws Exception {

//		请求处理方法对象
//		System.out.println(handler);
		
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			
//			判断是否是Aajx异步请求
			String XRequested = request.getHeader("X-Requested-With");
			
			if (XRequested != null && "XMLHttpRequest".equals(XRequested)) {
//				表明是Ajax异步请求
				response.getWriter().print("IsAjax");
			}else {
				response.sendRedirect(request.getContextPath()+"/login/toLogin");
			}
			return false;
		} else {
			return true;
		}
	}
	
}
