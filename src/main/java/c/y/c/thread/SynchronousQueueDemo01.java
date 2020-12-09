package c.y.c.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue：不存储元素的阻塞队列，也即单个的元素队列
 * <p>
 * SynchronousQueue没有容量
 * <p>
 * 与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue
 * <p>
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然
 */
public class SynchronousQueueDemo01 {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName() + "\t put b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName() + "\t put c");
                blockingQueue.put("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "put").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "take").start();


    }

}
