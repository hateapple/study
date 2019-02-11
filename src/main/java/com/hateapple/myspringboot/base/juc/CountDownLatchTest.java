package com.hateapple.myspringboot.base.juc;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch workOver = new CountDownLatch(5);
        final CountDownLatch startDoWork = new CountDownLatch(1);
        final Random r = new Random();

        for(int i=0; i<5; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //等所有线程都准备好,startDoWork调用countDown后开始跑
                        //业务代码
                        startDoWork.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(r.nextInt(10)*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我是业务代码。。。。。 " );
                    //当前线程执行完countDown一次
                    workOver.countDown();
                }
            }).start();
            System.out.println("第" + i + "个线程准备好了！");
        }
        //所有线程都准备好了,可以开始跑了
        startDoWork.countDown();

        //主线程调用await阻塞，指导workOver countDown到0
        workOver.await();
        System.out.println("all over");
    }
}
