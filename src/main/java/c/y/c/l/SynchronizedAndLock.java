package c.y.c.l;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：synchronized 与 lock 有什么区别？用新的lock有什么好处？你距离说说？
 * <p>
 * 1、原始构成
 * synchronized 是关键字属于JVM底层,
 * monitorenter(底层是通过monitor对象来完成，其实wait/notify等方法也以来于monitor对象只有在同步块或方法中才能调用wait/notify等方法)
 * monitorexit
 * Lock是具体类（java.util.concurrent.locks.Lock）是api层面的锁
 * <p>
 * 2、使用方法
 * synchronized 不需要用户手动释放锁，当synchronized代码执行完后系统会自动让线程释放对锁的占用
 * ReentrantLock 则需要用户去手动释放锁若没有主动释放锁，就有可能导致出现死锁现象
 * 需要lock()和unlock()方法配合try/finally语句块来完成
 * <p>
 * 3、等待是否可中断
 * synchronized不可中断，除非抛出异常或者正常运行完成
 * ReentrantLock可中断
 * 1、设置超时方法tryLock(long timeout, TimeUnit unit)
 * 2、lockInterruptibly()方代码块中，调用interrupt()方法可中断
 * <p>
 * 4、等待是否公平
 * synchronized非公平锁
 * ReentrantLock两者都可以，默认非公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 * <p>
 * 5、锁绑定多个条件Condition
 * synchronized没有
 * ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 * <p>
 * =========================================================================================================================
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * .......
 * 来10轮
 *
 * @author Administrator
 */
public class SynchronizedAndLock {

    public static void main(String[] args) throws InterruptedException {

        ShareResource shareResource = new ShareResource();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    shareResource.invokedAA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"AA").start();
            new Thread(() -> {
                try {
                    shareResource.invokedBB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"BB").start();
            new Thread(() -> {
                try {
                    shareResource.invokedCC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"CC").start();
        }

    }
}

class ShareResource {

    //A:1,B:2,C:3
    int num = 1;

    private Lock lock = new ReentrantLock();

    private Condition ca = lock.newCondition();
    private Condition cb = lock.newCondition();
    private Condition cc = lock.newCondition();


    public void invokedAA() throws InterruptedException {
        lock.lock();
        try {

            while (num != 1) {
                ca.await();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            num = 2;
            cb.signal();

        } finally {
            lock.unlock();
        }
    }

    public void invokedBB() throws InterruptedException {
        lock.lock();
        try {

            while (num != 2) {
                cb.await();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            num = 3;
            cc.signal();

        } finally {
            lock.unlock();
        }

    }

    public void invokedCC() throws InterruptedException {

        lock.lock();
        try {

            while (num != 3) {
                cc.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            num = 1;
            ca.signal();

        } finally {
            lock.unlock();
        }

    }

}