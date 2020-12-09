package c.y.c.l;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:ProdConsumer_BlockingQueueDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/9 9:04</p>
 * <p>@Version:1.0</p>
 */
public class ProdConsumer_BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        Resource resource = new Resource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            try {
                resource.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "producer").start();

        new Thread(() -> {
            resource.consumer();
        }, "consumer").start();

        TimeUnit.SECONDS.sleep(5);

        resource.stop();
    }

}

class Resource {

    private volatile boolean FLAG = true;

    private BlockingQueue<String> blockingQueue = null;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public Resource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void producer() throws InterruptedException {

        String data = null;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            boolean returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列成功 \t" + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列失败 \t" + data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t FLAG 为false，线程停止");

    }

    public void consumer() {
        while (FLAG) {
            String result = null;
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (null != result || "".equalsIgnoreCase(result)) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功消费数据 \t" + result);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t FLAG 为false，线程停止");
    }

    public void stop() {
        System.out.println(Thread.currentThread().getName() + "\t FLAG该为false，线程停止 ");
        this.FLAG = false;
    }

}
