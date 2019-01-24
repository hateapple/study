package com.hateapple.myspringboot.base.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 */
public class ClhSpinLock implements Lock {

    //当前线程节点
    private ThreadLocal<Node> node = null;
    //当前线程节点的前置线程节点
    private ThreadLocal<Node> prev = null;
    //
    private AtomicReference<Node> tail = new AtomicReference<>(new Node(Thread.currentThread()));

    ClhSpinLock(){
        this.node = ThreadLocal.withInitial(() -> new Node(Thread.currentThread()));
        this.prev = ThreadLocal.withInitial(() -> null);
    }

    @Override
    public void lock(){
        final Node node = this.node.get();
        node.locked = true;
        Node prev = this.tail.getAndSet(node);
        this.prev.set(prev);
        System.out.println("我是：" + node.threadName + "；我前面是：" + prev.threadName +";Prev状态为:"+prev.locked);
        while(prev.locked){

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public void unlock(){
        this.node.get().locked = false;
        System.out.print(this.node.get().threadName+";");
        //将node指向prev,让当前node被回收
        this.node.set(this.prev.get());
        System.out.println(this.node.get().threadName+";"+ " acquired the unlock!");

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    static class Node{

        Node(Thread thread){
            this.threadName = thread.getName();
        }
        private volatile boolean locked;
        private volatile String threadName;
    }

    public static void main(String[] args) throws InterruptedException {
        final ClhSpinLock clhSpinLock = new ClhSpinLock();
        clhSpinLock.lock();
        System.out.println("main lock");
        for(int i=0;i<10;i++){
            Thread thread = new Thread(() -> {
                clhSpinLock.lock();
                clhSpinLock.unlock();
            });
            thread.setName(String.valueOf(i));
            thread.start();
            Thread.sleep(1000);
        }
        System.out.println("main thread unlock!");
        clhSpinLock.unlock();
    }
}


