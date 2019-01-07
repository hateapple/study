package com.hateapple.myspringboot.base.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MyInvocationHandler implements InvocationHandler {

    private Object targetObject;

    MyInvocationHandler(Object object){
        this.targetObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用【前】业务处理。。。。。。");
        Object result = method.invoke(targetObject, args);
        System.out.println("调用【后】业务处理。。。。。。");
        return result;
    }
}
