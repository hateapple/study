package com.hateapple.myspringboot.base.juc;


import sun.tools.jconsole.Worker;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    final CyclicBarrier cyclicBarrier ;
    final Random r = new Random();

     class  Worker implements Runnable{
        private int codeNum;
        Worker(int codeNum){
            this.codeNum = codeNum;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(10)*1000);
                System.out.println(codeNum + "到达await");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    CyclicBarrierTest(int parties){
         this.cyclicBarrier = new CyclicBarrier(parties, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程都已到达await");
            }
        });

    }

    public void test(){
         for(int i=0; i< this.cyclicBarrier.getParties(); i++){
             Thread thread = new Thread(new Worker(i));
             thread.start();
         }
    }

    public static void main(String[] args) {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest(5);
        cyclicBarrierTest.test();

    }
}
