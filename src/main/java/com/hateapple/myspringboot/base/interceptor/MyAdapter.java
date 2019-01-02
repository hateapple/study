package com.hateapple.myspringboot.base.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor01 myInterceptor01;

    @Autowired
    private MyInterceptor02 myInterceptor02;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor01).addPathPatterns("/**");
        registry.addInterceptor(myInterceptor02).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
