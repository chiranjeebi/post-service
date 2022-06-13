package com.myfb.postservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class PostConfiguration  {

    @Value("${rest.call.connection.timeout:}")
    private Long connTimeout;    // this is applicable for any class annoted with,service,controller Configuration,componet,
  @Value("${rest.call.read.timeout:}")
    private Long readTimeout;    //we can read value  properties file in ur class
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){



        return builder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .build();

    }
}
