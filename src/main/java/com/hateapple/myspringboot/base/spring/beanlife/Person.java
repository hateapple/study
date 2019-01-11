package com.hateapple.myspringboot.base.spring.beanlife;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

@Component
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name = "默认";
    private Integer age = 0;

    public Person(){
        System.out.println("[构造器]调用Person的构造器实例化");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[BeanFactoryAware]");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("[BeanNameAware]");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("[DisposableBean]");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean]");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        System.out.println("[注入属性] 注入name属性" + name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("[注入属性] 注入age属性");
        this.age = age;
    }
}
