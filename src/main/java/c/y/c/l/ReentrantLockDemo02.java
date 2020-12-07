package c.y.c.l;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:ReentrantLockDemo02</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 9:34</p>
 * <p>@Version:1.0</p>
 */
public class ReentrantLockDemo02 {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Phone02(),"t1");
        Thread t2 = new Thread(new Phone02(),"t2");

        t1.start();
        t2.start();

    }

}

class Phone02 implements Runnable{

    Lock lock = new ReentrantLock();

    public void sendSMS(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked sendSMS");
            sendEmail();
        }finally {
            lock.unlock();
        }
    }

    public void sendEmail(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail");
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void run() {
        sendSMS();
    }
}
