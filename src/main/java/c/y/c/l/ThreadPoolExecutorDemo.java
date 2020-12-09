package c.y.c.l;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,
 * ThreadFactory threadFactory,
 * RejectedExecutionHandler handler) {
 * <p>
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
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(2,
                5,
                2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

    }

}
