package c.y.c.l;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始化为零的变量，两个线程对其交替操作，一个加1一个减1，重复5次
 *
 *
 * 注：多线程判断只能用while,不能用if
 */
public class ProdConsumer_TraditionalDemo {

    @SuppressWarnings("AlibabaAvoidManuallyCreateThread")
    public static void main(String[] args) {

        ShareData data = new ShareData();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "aa").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "cc").start();

        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();

    }

}

class ShareData {

    private int num = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {

            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }


}


