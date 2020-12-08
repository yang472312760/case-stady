package c.y.c.l;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS是什么? ==> compareAndSet
 * 比较->交换
 */
public class CASDemo {


    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t update value " + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + "\t update value " + atomicInteger.get());
    }

}
