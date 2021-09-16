package com.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8080")
		.allowedMethods("GET", "POST", "PUT", "DELETE");
		
//		registry.addMapping("/**").allowedOrigins("*")
//        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*")
//        .allowCredentials(true);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry regi) {
		
		regi.addResourceHandler("/images/**").addResourceLocations("file:/Users/hyeonjun/Desktop/myproject/images/");
		

		regi.addResourceHandler("/**").addResourceLocations("classpath:/static/").resourceChain(true)
				.addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						Resource requestedResource = location.createRelative(resourcePath);
						return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
								: new ClassPathResource("/static/index.html");
					}
				});
	}
	
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver cmpr = new CommonsMultipartResolver();
		cmpr.setDefaultEncoding("UTF-8"); // 업로드될 파일의 문자셋을 UTF-8 설정
		// 업로드될 파일의 크기 설정
		// 파일의 크기는 byte 단위로 설정이 가능함(10 * 1024 * 1024 = 10Mbyte)
		cmpr.setMaxUploadSizePerFile(10 * 1024 * 1024);
		
		return cmpr;
	}
}
