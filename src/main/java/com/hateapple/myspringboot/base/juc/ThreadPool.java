package com.hateapple.myspringboot.base.juc;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {

    private ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(5,//核心线程数
                    10,//最大线程数
                    1000,//线程池中超过corePoolSize的线程的空闲存活时间
                    TimeUnit.SECONDS,//存活时间单位
                    new LinkedBlockingDeque<>(5),//阻塞任务队列
                    new MyThreadFactory(),//新建线程工厂
                    new ThreadPoolExecutor.AbortPolicy()//超出最大线程数的处理策略
            );


    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        ThreadPoolExecutor threadPoolExecutor = threadPool.getThreadPoolExecutor();
        for(int i=0;i<10;i++){
            System.out.println("提交第" + i + "个任务!");
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "  running=====");
                }
            });
        }
    }


}

class MyThreadFactory implements ThreadFactory{

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String threadName = "线程-" + count.addAndGet(1);
        t.setName(threadName);
        return t;
    }
}