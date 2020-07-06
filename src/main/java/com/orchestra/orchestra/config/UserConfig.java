package com.orchestra.orchestra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Order(0)
@Configuration
public class UserConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.
                jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password from users where email=?")
                .authoritiesByUsernameQuery("select email,password from users where email=? and password=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/test").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();

        httpSecurity.cors();
    }
}
