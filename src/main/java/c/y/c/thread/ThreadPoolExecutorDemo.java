package c.y.c.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {}
 * <p>
 * corePoolSize：线程池中的常驻核心线程数
 * <p>
 * maximumPoolSize：线程池能够容纳同时执行的最大线程数，此值必须大于等于1.
 * <p>
 * keepAliveTime：多余的空闲线程的存活时间。
 * 当线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，
 * 多余空闲线程会被销毁直到只剩下corePoolSize个线程为止
 * <p>
 * unit：keepAliveTime的单位
 * <p>
 * workQueue：任务队列，被提交但尚未被执行的任务
 * <p>
 * threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可
 * <p>
 * handler：拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数（maximumPoolSize）
 * <p>
 * <p>
 * 拒绝策略是什么？
 * <p>
 * 等待队列已经满了，在也塞不下新任务，同时，线程池中max线程也达到了最大数，无法继续为新任务服务
 * 这时我们就需要拒绝策略机制合理的处理这个问题。
 * <p>
 * JDK内置的拒绝策略有哪些？
 * AbortPolicy（默认）：直接抛出RejectedExecutionException异常阻止系统正常运行
 * CallerRunsPolicy；“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退给调用者，从而降低新任务的流量。
 * DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
 * DiscardPolicy：直接丢弃任务，不予任何处理也不抛出异常，如果允许任务丢失，这是最好的一种方案
 * <p>
 * JDK提供的3中创建线程池方式一个都不用，容易出OOM
 * <p>
 * 合理配置线程池你是如何考虑的？
 * <p>
 * CPU密集型
 * <p>
 * CPU密集型的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行
 * CPU密集任务只有在真正的多核CPU上才能得到加速（通过多线程）
 * <p>
 * CPU密集型任务配置尽可能少的线程数量：一般公式：CPU核数+1个线程的线程池
 * <p>
 * IO密集型
 * <p>
 * 一、由于IO密集型任务线程并不是一直在执行任务，则应配置尽可能多的线程，如CPU核数*2
 * <p>
 * 二、IO密集型，即该任务需要大量的IO，即大量的阻塞
 * 在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力浪费在等待。
 * 所以在IO密集型任务中使用多线程可以大大的加速程序运行，即使在单核CPU上，这种加速主要就是利用了被浪费掉的阻塞时间
 * <p>
 * IO密集型时，大部分线程都阻塞，故需要多配置线程数
 * <p>
 * 参考公式：CPU核数/1-阻塞系数         阻塞系数在0.8~0.9之间
 * <p>
 * 比如8核CPU：8/1-0.9=80个线程数
 *
 * @author Administrator
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(2,
                5,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3), Executors.defaultThreadFactory(),
                //AbortPolicy如果超出maximumPoolSize+workQueue最大值，直接报RejectedExecutionException异常
                //new ThreadPoolExecutor.AbortPolicy());
                //new ThreadPoolExecutor.CallerRunsPolicy());
                //new ThreadPoolExecutor.DiscardOldestPolicy());
                new ThreadPoolExecutor.DiscardPolicy());

        try {

            for (int i = 0; i < 10; i++) {
                service.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

    }

}
