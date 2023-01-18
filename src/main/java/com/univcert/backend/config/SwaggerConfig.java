package com.univcert.backend.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String API_NAME = "대학인증 API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "대학인증 API 명세서\n" +
            "1.에러 코드 Response가 없는 API는 실패시 Error 로그 ";



    @Bean
    public Docket testApi(){
        return getDocket("TEST",Predicates.or(
                PathSelectors.regex("/api.*")));
    }
    @Bean
    public Docket AllApi() {
        return getDocket("All", Predicates.or(
                PathSelectors.regex("/*.*")));

    }
    public ApiInfo apiInfo() {  // API의 이름, 현재 버전, API에 대한 정보
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }
    public Docket getDocket(String groupName, Predicate<String> predicate) {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.univcert.backend"))
                .paths(predicate)
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    //swagger ui 설정.
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .displayRequestDuration(true)
                .validatorUrl("")
                .build();
    }


}

