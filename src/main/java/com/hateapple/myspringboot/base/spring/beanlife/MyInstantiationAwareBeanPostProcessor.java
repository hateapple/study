package com.hateapple.myspringboot.base.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;


@Component
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    MyInstantiationAwareBeanPostProcessor(){
        super();
        System.out.println("【MyInstantiationAwareBeanPostProcessor】的构造方法");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[InstantiationAwareBeanPostProcessorAdapter]接口postProcessBeforeInstantiation");
        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[InstantiationAwareBeanPostProcessorAdapter]接口postProcessAfterInstantiation");
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[InstantiationAwareBeanPostProcessorAdapter]接口postProcessProperties方法");
        }
        return super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[InstantiationAwareBeanPostProcessorAdapter]接口postProcessBeforeInitialization方法");
        }
        return super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)){
            System.out.println("[InstantiationAwareBeanPostProcessorAdapter]接口postProcessAfterInitialization方法");
        }
        return super.postProcessAfterInitialization(bean, beanName);
    }


}
