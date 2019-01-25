# 1.自旋锁的意义

虚拟机对锁的一种优化；

由于互斥锁需要阻塞线程，线程的挂起与恢复都需要从用户态切换到内核态中完成,这些操作会给系统的并发性带去很大压力。考虑到共享数据的锁状态只会持续很短一段时间，为了这段时间挂起、恢复线程很不值得，因此出现了自旋锁。顾名思义，等待获取锁的线程不停的自旋，直到能够获取到锁。



# 2.CLH自旋锁

```java
/**
 * 自旋锁
 */
public class ClhSpinLock {

    //当前线程节点
    private ThreadLocal<Node> node = null;
    //当前线程节点的前置线程节点
    private ThreadLocal<Node> prev = null;
    //当前隐式链表的最后节点
    private AtomicReference<Node> tail = new AtomicReference<>(new Node(Thread.currentThread()));

    ClhSpinLock(){
        //初始化当前节点，默认locked为false
        this.node = new ThreadLocal<Node>(){
             protected Node initialValue(){
                 return new Node(Thread.currentThread());
             }
        };
        this.prev = new ThreadLocal<>();
    }

     public void lock(){
        //get()方法，如果当前线程对应的ThreadLocalMap不存在会调用setInitialValue
         //获取初始化的ThreadLocalMap，其中会通过initialValue获取到value,即构造方法中重写的
         //initalValue方法
        final Node node = this.node.get();
        node.locked = true;
        Node prev = this.tail.getAndSet(node);
        this.prev.set(prev);
        System.out.println("我是：" + node.threadName + "；我前面是：" + prev.threadName +";Prev状态为:"+prev.locked);
        while(prev.locked){

        }
    }


     public void unlock(){
        this.node.get().locked = false;
        System.out.print(this.node.get().threadName+";");
        //将node指向prev,让当前node被回收
        this.node.set(this.prev.get());
        System.out.println(this.node.get().threadName+";"+ " acquired the unlock!");

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

        for(int i=0;i<5;i++){
            Thread thread = new Thread(() -> {

                clhSpinLock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clhSpinLock.unlock();
            });
            thread.setName(String.valueOf(i));
            thread.start();
            Thread.sleep(1000);
        }
        clhSpinLock.unlock();
    }
}
```



