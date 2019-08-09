package com.lanit.lkz_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    public SpringSecurityConfig() {
        super();
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");

    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("jim").password("{noop}demo").roles("ADMIN").and()
                .withUser("bob").password("{noop}demo").roles("USER").and()
                .withUser("ted").password("{noop}demo").roles("USER", "ADMIN");
    }


}