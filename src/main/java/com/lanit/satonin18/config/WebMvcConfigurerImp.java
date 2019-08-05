package com.lanit.satonin18.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.PostConstruct;


@Configuration
@ComponentScans( value = {
        @ComponentScan(
                basePackages = {"com.lanit.satonin18.app.controller"}
        )
})
@EnableWebMvc
public class WebMvcConfigurerImp implements WebMvcConfigurer {

    @PostConstruct
    private void p() {
        System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwww");
    }

    @Bean
    public InternalResourceViewResolver resolver() {
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");

        return resolver;
    }
}
