package com.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerSurvice;
import com.memo.interceptor.PermissonInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	@Autowired
	private PermissonInterceptor permissonInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(permissonInterceptor)
		.addPathPatterns("/**")		// **아래 디렉토리까지 확인
		.excludePathPatterns("/user/sign_out","/static/**","/error") ; //여기에 해당하는 URL은 인터셉터를 타지 않는다. 
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**") //http://localhost/images/.. 와 같이 접근 가능하게 맵핑한다.
		.addResourceLocations("file:///"+FileManagerSurvice.FILE_UPLOAD_PATH);
		//실제 파일 저장위치
	}

}
