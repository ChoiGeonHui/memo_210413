package com.memo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class PermissonInterceptor implements HandlerInterceptor{
	
	//private Logger logger = LoggerFactory.getLogger(PermissonInterceptor.class);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//���� Ȯ�� =>������ �α��ε� ����
		HttpSession session = request.getSession();
		String userloginId = (String) session.getAttribute("userLoginId");
		
		//url -url path Ȯ��
		String uri =request.getRequestURI();
		logger.info("######### userloginId: "+ userloginId);
		//��α��� && /post => �α��� �������� �����̷�Ʈ
		if(userloginId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// �α��� && /user =>����Ʈ �������� �����̷�Ʈ
		if(userloginId != null && uri.startsWith("/user")) {
			response.sendRedirect("/post/post_list_view");
			return false;
		}
		
		return true;
	}
		
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("#### posthandle: "+uri);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		System.out.println("#### afterComletion: "+uri);
		
	}
	
	

}
