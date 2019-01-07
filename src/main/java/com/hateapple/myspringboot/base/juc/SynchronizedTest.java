package com.hateapple.myspringboot.base.juc;


import java.util.ArrayList;

/**
 * Synchronized关键字测试
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> list = new ArrayList<>();
        list.iterator();
        list.listIterator();
        LockStaticCode syncTest = new LockStaticCode();
        Thread t1 = new Thread(syncTest);
        Thread t2 = new Thread(syncTest);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(syncTest.ctl);
    }
}

class LockInstance implements Runnable{
    static int ctl = 0;
    //锁 LockInstance的对象
    private synchronized  void increase(){
        ctl++;
    }
    @Override
    public void run() {
        for(int i=0;i<10000;i++){
            increase();
        }
    }
}

class LockClass implements Runnable{
    static int ctl = 0;
    //锁 LockCLass.class
    private synchronized  static void increase(){
        ctl++;
    }
    @Override
    public void run() {
        for(int i=0;i<100000;i++){
            increase();
        }
    }
}

class LockStaticCode implements Runnable{
    private static Byte lock = new Byte((byte)1);
    static int ctl = 0;
    //锁 LockCLass.class
    private  static void increase(){
        //synchronized (this)
        //synchronized (LockStaticCode.class)
        synchronized (lock){
            ctl++;
        }
    }
    @Override
    public void run() {
        for(int i=0;i<100000;i++){
            increase();
        }
    }
}