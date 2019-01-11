package com.hateapple.myspringboot.pattern;

/**
 * 单例模式
 */
public class Singleton {

}

/**
 * 懒汉模式，线程不安全
 */
class LazySingleton_01{
    private static LazySingleton_01 lazySingleton = null;
    private LazySingleton_01(){ }
    public LazySingleton_01 getInstance(){
        if(null == lazySingleton){
            lazySingleton = new LazySingleton_01();
        }
        return lazySingleton;
    }
}

/**
 * 懒汉模式，线程安全
 */
class LazySingleton_02{
    private static volatile  LazySingleton_02 lazySingleton = null;
    private LazySingleton_02(){ }
    public synchronized LazySingleton_02 getInstance(){
        if(null == lazySingleton){
            lazySingleton = new LazySingleton_02();
        }
        return lazySingleton;
    }
}

/**
 * 懒汉模式,线程安全
 */
class LazySingleton_03{
    private static volatile LazySingleton_03 lazySingleton = null;
    private LazySingleton_03(){ }
    public  LazySingleton_03 getInstance(){
        if(null == lazySingleton){
            synchronized (LazySingleton_03.class){
                if(null == lazySingleton){
                    lazySingleton = new LazySingleton_03();
                }
            }
        }
        return lazySingleton;
    }
}

/**
 * holder模式，线程安全
 */
class LazySingleton_04{
    static class InstanceHolder{
        private static LazySingleton_04 lazySingleton = new LazySingleton_04();
    }
    private LazySingleton_04(){ }
    public  LazySingleton_04 getInstance(){
        return InstanceHolder.lazySingleton;
    }
}

/**
 * 饿汉模式
 */
class LazySingleton_05{
    private static LazySingleton_05 lazySingleton = new LazySingleton_05();
    private LazySingleton_05(){ }
    public LazySingleton_05 getInstance(){
        return lazySingleton;
    }
}