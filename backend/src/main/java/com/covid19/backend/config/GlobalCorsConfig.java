package com.covid19.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 开启全局允许跨域访问
 *
 * @author billchen
 * @version 1.0
 * @create 2020-08-22 20:14
 **/

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(true);

	}
}