package c.y.c.l;

import java.util.concurrent.TimeUnit;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:Volatile</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/4 10:22</p>
 * <p>@Version:1.0</p>
 */
public class Volatile {

    public static void main(String[] args) {
        Number number = new Number();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t com in");

            try {
                TimeUnit.SECONDS.sleep(3);

                number.numAddTo60();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t num is \t" + number.num);
        }, "aaa").start();

        while (number.num == 0) {
        }

        System.out.println(Thread.currentThread().getName() + "\t num is \t" + number.num);

    }

}

class Number {

    volatile int num = 0;

    public void numAddTo60() {
        this.num = 60;
    }
}
