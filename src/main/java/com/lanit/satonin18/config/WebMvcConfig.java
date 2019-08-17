package com.lanit.satonin18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

@Configuration
@ComponentScans( value = {
        @ComponentScan(
                basePackages = {"com.lanit.satonin18.app.controller"}
        )
})
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    @PostConstruct
    private void p() {
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww");
        String admin = "admin";

        System.out.println(admin);
        String encoded = new BCryptPasswordEncoder().encode(admin);
        System.out.println(encoded);

        boolean b = new BCryptPasswordEncoder().matches(admin, encoded);
        System.out.println(b);
    }

//    @Bean
//    public InternalResourceViewResolver resolver() {
//        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
//
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//
//        return resolver;
//    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry
                .jsp()
                .prefix("/WEB-INF/views/")
                .suffix(".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addViewController("/login")
                .setViewName("input");
    }
}
