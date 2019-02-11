# 一.CountDownLatch作用

```java
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch workOver = new CountDownLatch(5);
        final CountDownLatch startDoWork = new CountDownLatch(1);
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
                    System.out.println("我是业务代码。。。。。 " );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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
```



# 二、CountDownLatch源码解析



## 2.1.构造方法

CountDownLatch的构造方法只有一个:

```java
/*the number of times {@link #countDown} must be invoked
  before threads can pass through {@link #await}
  
  count值表示调用count次countDown方法后，调用await方法的线程才能继续执行
*/
public CountDownLatch(int count) {
    if (count < 0) throw new IllegalArgumentException("count < 0");
    this.sync = new Sync(count);
}
```

构造方法看起来很简单，又创建了个Sync,我们来看Sync,Sync是CountDownLatch的静态内部类,继承与AQS

```java
private static final class Sync extends AbstractQueuedSynchronizer {
    //序列化版本
    private static final long serialVersionUID = 4982264981922014374L;

    Sync(int count) {
        setState(count);
    }

    int getCount() {
        return getState();
    }

    protected int tryAcquireShared(int acquires) {
        return (getState() == 0) ? 1 : -1;
    }

    protected boolean tryReleaseShared(int releases) {
        // Decrement count; signal when transition to zero
        for (;;) {
            int c = getState();
            if (c == 0)
                return false;
            int nextc = c-1;
            if (compareAndSetState(c, nextc))
                return nextc == 0;
        }
    }
}
```