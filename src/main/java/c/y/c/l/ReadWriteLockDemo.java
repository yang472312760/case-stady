package c.y.c.l;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:ReadWriteLockDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 13:35</p>
 * <p>@Version:1.0</p>
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + " ", temp + " ");
            }, "aa").start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + " ");
            }, "bb").start();
        }

    }

}

class MyCache {

    private static Map map = new HashMap();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 数据正在写入" + key);
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 数据写入完成");
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 数据正在读取");
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 数据读取成功" + value);
        } finally {
            lock.readLock().unlock();
        }
    }

}
