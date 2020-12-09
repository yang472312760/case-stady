package c.y.c.thread;

import java.util.concurrent.CountDownLatch;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:CountDownLatchDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 14:34</p>
 * <p>@Version:1.0</p>
 */
public class CountDownLatchDemo {

    private static final int COUNT_DOWN_NUM = 6;

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_NUM);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t被灭");
                countDownLatch.countDown();
            }, CountryEnum.getCountryName(i).getName()).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t 程序执行完毕");

    }

    private static void test01() {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT_DOWN_NUM);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t倒计时");
                    countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t 程序执行完毕");
    }

}
