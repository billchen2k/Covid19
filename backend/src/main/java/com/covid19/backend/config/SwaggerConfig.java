package com.covid19.backend.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ConditionalOnExpression("${swagger.enable:true}") //当enable为true时才选择加载该配置类
@EnableSwagger2
public class SwaggerConfig{

	@Bean
	public Docket createMainApi() {
		List<ResponseMessage> responseMessageList = new ArrayList<>();
		Arrays.stream(ErrorEnums.values()).forEach(errorEnums -> {
			responseMessageList.add(
					new ResponseMessageBuilder().code(errorEnums.getCode()).message(errorEnums.getMsg()).responseModel(
							new ModelRef("string")).build()
			);
		});
		return new Docket(DocumentationType.SWAGGER_2)
				//.groupName("API")//如果要写多个docket，就需要为它们指定不同groupName
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.covid19.backend"))//设定扫描范围
				.paths(PathSelectors.any())
				.build()
				.globalResponseMessage(RequestMethod.GET, responseMessageList)
				.globalResponseMessage(RequestMethod.POST, responseMessageList)
				.globalResponseMessage(RequestMethod.PUT, responseMessageList)
				.globalResponseMessage(RequestMethod.DELETE, responseMessageList);
	}

//	@Bean
//	public Docket createModelApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("Model")//如果要写多个docket，就需要为它们指定不同groupName
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("ink.jiyan.backend.model"))//设定扫描范围
//				.paths(PathSelectors.any())
//				.build();
//	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Covid19 API")//文档名
				.description("Covid-19 疫情分析与管理系统")//文档描述
				.termsOfServiceUrl("数据源")//数据源（默认http://localhost:8080/v2/api-docs）
				.version("1.0")
				.build();
	}
}