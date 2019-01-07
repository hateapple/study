package com.hateapple.myspringboot.base.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    MyBeanFactoryPostProcessor(){
        super();
        System.out.println("【MyBeanFactoryPostProcessor】的构造方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition =
                beanFactory.getBeanDefinition("person");
        System.out.println("修改person的默认值");
        beanDefinition.getPropertyValues().addPropertyValue("name", "杭州");
    }
}
