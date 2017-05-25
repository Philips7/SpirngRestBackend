package com.nmp.ArgumentedReality.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Dominik on 2017-05-24.
 */
@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("localhost:4200")
                .allowedMethods("GET")
                .allowedHeaders("authorization", "content-type", "serverHeader")
                .exposedHeaders("authorization", "content-type", "serverHeader")
                .allowCredentials(false).maxAge(3600);
    }


}
