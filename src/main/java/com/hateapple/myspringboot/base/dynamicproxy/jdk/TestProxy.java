package com.hateapple.myspringboot.base.dynamicproxy.jdk;

import java.lang.reflect.Proxy;

public class TestProxy {

    public static void main(String[] args) {
        PersonImpl person = new PersonImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(person);
        Class<?> [] clazz = {Person.class};
        Person peronProxy = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), clazz, myInvocationHandler);
        peronProxy.sayHello();
    }
}
