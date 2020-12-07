package c.y.c.l;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:Volatile02</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/4 13:44</p>
 * <p>@Version:1.0</p>
 */
public class Volatile02 {

    public static void main(String[] args) {

        Number02 number02 = new Number02();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    number02.numberPlusPlus();
                    number02.atomicPlusPlus();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(number02.num);
        System.out.println(number02.atomicInteger.get());
    }

}

class Number02 {

    int num = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void numberPlusPlus() {
        num++;
    }

    public void atomicPlusPlus() {
        atomicInteger.getAndIncrement();
    }

}
