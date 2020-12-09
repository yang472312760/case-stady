package c.y.c.thread;


import java.util.concurrent.TimeUnit;

/**
 * 死锁分析
 * <p>
 * 产生死锁的原因
 * <p>
 * 死锁是指两个或者两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，若无外力干涉那它们都将无法推进下去
 * 如果系统资源充足，进程的资源请求都能得到满足，死锁出现的可能性就很低，否则就会因争夺有限的资源而陷入死锁
 *
 * 解决：
 * jps命令定位进程号 jps -l
 * jstack找到死锁查看 jstack 进程号
 *
 * @author Administrator
 */

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldThread(lockA, lockB), "threadAAA").start();
        new Thread(new HoldThread(lockB, lockA), "threadBBB").start();

    }

}

class HoldThread implements Runnable {

    String lockA;
    String lockB;

    public HoldThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己拥有" + lockA + "\t尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己拥有" + lockB + "\t尝试获取" + lockA);
            }
        }
    }
}
