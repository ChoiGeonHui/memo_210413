package com.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.interceptor.PermissonInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	@Autowired
	private PermissonInterceptor permissonInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissonInterceptor)
		.addPathPatterns("/**")		// **�Ʒ� ���丮���� Ȯ��
		.excludePathPatterns("/user/sign_out","/static/**","/error") ; //���⿡ �ش��ϴ� URL�� ���ͼ��͸� Ÿ�� �ʴ´�. 
	}

}
