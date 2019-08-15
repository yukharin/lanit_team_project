package com.lanit.satonin18.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .authorizeRequests().antMatchers("/login**").permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) {
//        try {
//            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            auth
//                    .inMemoryAuthentication()
//                    .passwordEncoder(passwordEncoder)
//                    .withUser("user").password(passwordEncoder.encode("user")).roles("USER");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/*").permitAll()
//                .and()
//
//                .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login")
//                .loginPage("/singers")
//                .failureUrl("/security/loginfail")
//                .defaultSuccessUrl("/singers")
//                .permitAll()
//                .and()
//
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/singers")
//                .and()
//
//                .csrf().disable();
//        //csrfTokenRepository(repo());
//    }
//
//    //@Bean
//    public CsrfTokenRepository repo() {
//        HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
//        repo.setParameterName("_csrf");
//        repo.setHeaderName("X-CSRF-TOKEN");
//        return repo;
//    }
}
