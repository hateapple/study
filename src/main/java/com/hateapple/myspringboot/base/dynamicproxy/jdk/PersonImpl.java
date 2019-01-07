package com.hateapple.myspringboot.base.dynamicproxy.jdk;

public class PersonImpl implements Person {
    @Override
    public String sayHello() {
        return "李雷说Hello";
    }

    @Override
    public String sayHi() {
        return "李雷说Hi";
    }
}
