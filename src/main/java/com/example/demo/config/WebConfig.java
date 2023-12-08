package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
class WebConfig implements WebFluxConfigurer {

    //DEBUG와 TRACE 로그 레벨에 민감 정보가 있음, 이 것은 해당 로그를 명시적으로 활성화 함 (WebClient 로그에 사용)
    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().enableLoggingRequestDetails(false);
    }

}