package com.nmp.ArgumentedReality.config;

import com.nmp.ArgumentedReality.filter.CustomFilter;
import com.nmp.ArgumentedReality.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


/**
 * Created by Dominik on 2017-05-25.
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    @Qualifier("UserService")
    private UserDetailsService userDetailsService;


//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
//        authenticationManagerBuilder
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public CustomFilter authenticationTokenFilterBean() throws Exception{
        return new CustomFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user2 where username=?")
                .authoritiesByUsernameQuery("select username, role from user_role2 where username=?");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasRole("USER")
//                .antMatchers(HttpMethod.PUT,"/users").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/users").hasRole("USER");

        http.addFilterBefore(authenticationTokenFilterBean(),UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
    }
}


