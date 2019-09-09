package com.lanit.satonin18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.*;

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
public class WebMvcConfig
        implements WebMvcConfigurer {

    @PostConstruct
    private void p() {
//        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww");
    }

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/views/");
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry
//                .jsp()
//                .prefix("/WEB-INF/views/")
//                .suffix(".jsp");
//    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry
//                .addViewController("/login")
//                .setViewName("inputForm");
//    }

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/resources/**", "/lkz/**")
                .addResourceLocations("/resources/", "/lkz/");
	}
}
