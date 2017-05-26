package com.nmp.ArgumentedReality;

import com.nmp.ArgumentedReality.config.DispatcherServletInitializer;
import com.nmp.ArgumentedReality.filter.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.support.MultipartFilter;

import java.text.SimpleDateFormat;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.nmp.ArgumentedReality"})
public class ArgumentedRealityApplication {

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	@Order(-11111) // same with @Order(0)
	public MultipartFilter multipartFilter() {
		return new MultipartFilter();
	}

	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		return b;
	}


	public static void main(String[] args) {
		SpringApplication.run(new Class[]{ArgumentedRealityApplication.class,
				DispatcherServletInitializer.class} , args);
	}
}

