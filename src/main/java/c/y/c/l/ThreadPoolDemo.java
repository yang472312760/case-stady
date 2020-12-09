package c.y.c.l;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 为什么用线程池，优势？
 * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，
 * 如果线程数量超过了最大的数量超出数量的线程排队等候，等其他线程执行完毕，在从队列中取出任务来执行。
 * <p>
 * 他的主要特点为：线程复用；控制最大并发数；管理线程
 * <p>
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，
 * 使用线程池可以进行统一的分配，调优和监控
 * <p>
 * <p>
 * 线程池的种类
 * 1、Executors.newScheduledThreadPool(); 资源调度线程池
 * 2、Executors.newWorkStealingPool(int);
 * *3、Executors.newFixedThreadPool(int);
 * 一池固定线程数，执行长期任务，性能好很多
 * *4、Executors.newSingleThreadExecutor();
 * 一池一线程，一个任务一个任务执行的场景。
 * *5、Executors.newCacheThreadPool();
 * 一池N线程，适用：执行很多短期异步的小程序或者负载较轻的服务器
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        //ExecutorService service = Executors.newFixedThreadPool(5);
        //ExecutorService service = Executors.newSingleThreadExecutor();
        ExecutorService service = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t");

                });
                try {
                    TimeUnit.MICROSECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {

        } finally {
            service.shutdown();
        }

    }
}
