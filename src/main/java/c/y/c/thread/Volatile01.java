package c.y.c.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Volatile01 {

    public static void main(String[] args) {

        Number01 number01 = new Number01();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    number01.numPlusPlus();
                    number01.atomicInteger();
                }
            }).start();

        }

        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        System.out.println("数字num:" + number01.num);
        System.out.println("atomicInteger:" + number01.atomicInteger);
    }

}

class Number01 {

    int num = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void numPlusPlus() {
        num++;
    }

    public void atomicInteger(){
        atomicInteger.getAndIncrement();
    }


}
