package com.percentmoves.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PropertyConfig propertyConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
            .authorizeRequests().antMatchers("/login").hasRole(propertyConfig.getProp().getProperty("role1"))
            .antMatchers("/login").hasRole(propertyConfig.getProp().getProperty("role2"))
            .antMatchers("/login").hasRole(propertyConfig.getProp().getProperty("role3"))
            .anyRequest().authenticated()
            .and()
            .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(propertyConfig.getProp().getProperty("user1"))
                .password("{noop}"+propertyConfig.getProp().getProperty("pw1"))
                .roles(propertyConfig.getProp().getProperty("role1"))
                .and()
                .withUser(propertyConfig.getProp().getProperty("user2"))
                .password("{noop}"+propertyConfig.getProp().getProperty("pw2"))
                .roles(propertyConfig.getProp().getProperty("role2"))
                .and()
                .withUser(propertyConfig.getProp().getProperty("user3"))
                .password("{noop}"+propertyConfig.getProp().getProperty("pw3"))
                .roles(propertyConfig.getProp().getProperty("role3"))
        ;
    }

}
