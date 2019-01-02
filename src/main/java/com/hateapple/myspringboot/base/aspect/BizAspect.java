package com.hateapple.myspringboot.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class BizAspect{
     @Pointcut("@annotation(com.hateapple.myspringboot.base.aspect.CrateLog)")
     public void excudeService(){

     }

     @Around("excudeService()")
     public Object aroundLog(ProceedingJoinPoint joinpoint) {
         Object result = null;
         try {
             System.out.println("环绕通知开始 日志记录");
             long start = System.currentTimeMillis();
             //有返回参数 则需返回值
             result =  joinpoint.proceed();
             long end = System.currentTimeMillis();
             System.out.println("总共执行时长" + (end - start) + " 毫秒");
             System.out.println("环绕通知结束 日志记录");
         } catch (Throwable t) {
             System.out.println("出现错误");
         }
         return result;
     }
}
