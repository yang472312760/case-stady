package c.y.c.l;

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
 * @author Administrator
 */
public class SynchronizedAndLock {

    public static void main(String[] args) {

        synchronized (new Object()) {
        }

        new ReentrantLock();

    }
}
