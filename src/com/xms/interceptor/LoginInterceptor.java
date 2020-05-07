package com.xms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xms.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
		HttpServletResponse response,Object handler) throws Exception {

//		������������
//		System.out.println(handler);
		
		User user = (User) request.getSession().getAttribute("user");
		
		if (user == null) {
			
//			�ж��Ƿ���Aajx�첽����
			String XRequested = request.getHeader("X-Requested-With");
			
			if (XRequested != null && "XMLHttpRequest".equals(XRequested)) {
//				������Ajax�첽����
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
