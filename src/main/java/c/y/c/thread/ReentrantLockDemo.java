package c.y.c.thread;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:ReentrantLockDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/7 9:21</p>
 * <p>@Version:1.0</p>
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();

        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();

    }

}

class Phone {

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS ");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail ");
    }

}
