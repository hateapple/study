package com.hateapple.myspringboot.base.dynamicproxy.cglib;


import com.hateapple.myspringboot.base.dynamicproxy.jdk.PersonImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PersonProxyCglib implements MethodInterceptor {
    private Object targetObject;

    public Object getInstance(Object object){
        this.targetObject = object;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理业务【前】处理。。。。");
        methodProxy.invoke(targetObject, objects);
        System.out.println("cglib代理业务【后】处理。。。。");
        return null;
    }

    public static void main(String[] args) {
        PersonImpl person = new PersonImpl();
        PersonProxyCglib personProxyCglib = new PersonProxyCglib();
        PersonImpl person1 = (PersonImpl)personProxyCglib.getInstance(person);
        person1.sayHello();
    }
}
