package com.lanit.lkz_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig {

    //    public SpringSecurityConfig() {
//        super();
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//
//    @Bean
//    public AuthenticationManager customAuthenticationManager() throws Exception {
//        return authenticationManager();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests().antMatchers(
//                "/css/**", "/image/**", "/js/**", "/registration").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin().loginPage("/login").permitAll().loginProcessingUrl("/doLogin")
//                .and()
//                .logout().permitAll().logoutUrl("/logout")
//                .and()
//                .rememberMe().tokenValiditySeconds(604800).key("lssAppKey").rememberMeCookieName("cookie-me").rememberMeParameter("remember");
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
//    }

}