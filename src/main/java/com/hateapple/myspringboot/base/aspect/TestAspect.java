package com.hateapple.myspringboot.base.aspect;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

@Service
public class TestAspect implements BeanNameAware {
    @CrateLog
    public void testAspect(){
        System.out.println("testAspect-------");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("name--------" + s);
            testAspect();
    }
}
