package c.y.c.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {

        System.out.println("ABA问题产生=======================================");

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            System.out.println(atomicReference.compareAndSet(100, 2020) + "\t" + atomicReference.get());

        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ABA问题解决=================================");

        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "\t第一次版本号\t" + atomicStampedReference.getStamp());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t第二次版本号\t" + atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);

            System.out.println(Thread.currentThread().getName() + "\t第三次版本号\t" + atomicStampedReference.getStamp());

        }, "t3").start();


        new Thread(() -> {

            int stamp = atomicStampedReference.getStamp();

            System.out.println(Thread.currentThread().getName() + "\t第一次版本号\t" + stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "\t是否改变成功" + atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1) + "\t当前版本号为\t" + atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t当前值为\t" + atomicStampedReference.getReference());

        }, "t4").start();

    }

}
