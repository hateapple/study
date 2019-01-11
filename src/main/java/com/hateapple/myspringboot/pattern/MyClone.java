package com.hateapple.myspringboot.pattern;

import com.hateapple.myspringboot.base.spring.beanlife.Person;

public class MyClone implements Cloneable {

    private Person p;

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    MyClone(){
        System.out.println("原型模式创建成功");
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        MyClone mc = new MyClone();
        mc.setP(new Person());
        MyClone mc2 = (MyClone)mc.clone();
        System.out.println(mc == mc2);
        //浅copy
        System.out.println(mc.getP() == mc2.getP());
    }
}
