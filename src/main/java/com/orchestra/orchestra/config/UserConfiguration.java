package com.orchestra.orchestra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Order(0)
@EnableWebSecurity
@Configuration
public class UserConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws Exception {
        //authentication code
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email,password,active from users where email=?")
                .authoritiesByUsernameQuery("select email,password from users where email=?")
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/test").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin/signup").permitAll()
                .antMatchers("/admin/login").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
        httpSecurity.cors();

        httpSecurity.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }
}
