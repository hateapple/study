package com.hateapple.myspringboot.base.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    MyBeanPostProcessor(){
        System.out.println("【MyBeanPostProcessor】的构造方法");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[BeanPostProcessor]接口postProcessorBeforeInitialization修改属性");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[BeanPostProcessor]接口postProcessAfterInitialization修改属性");
        }
        return bean;
    }
}
