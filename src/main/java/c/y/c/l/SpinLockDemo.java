package c.y.c.l;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:SpinLockDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 10:42</p>
 * <p>@Version:1.0</p>
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {

        Thread t = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "\t the lock come in");

        while (!atomicReference.compareAndSet(null, t)) {
        }



    }

    public void unlock() {

        Thread t = Thread.currentThread();

        System.out.println(Thread.currentThread().getName() + "\t the lock unlock");

        atomicReference.compareAndSet(t, null);
    }

    public static void main(String[] args) {

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "aa").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unlock();
        }, "bb").start();

    }

}
