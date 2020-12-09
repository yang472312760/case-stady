package c.y.c.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * <p>@ProjectName:stady-case</p>
 * <p>@Package:c.y.c.l</p>
 * <p>@ClassName:CallableDemo</p>
 * <p>@Description:${description}</p>
 * <p>@Author:yang</p>
 * <p>@Date:2020/12/9 10:01</p>
 * <p>@Version:1.0</p>
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread());
        Thread thread = new Thread(futureTask);

        thread.start();

        System.out.println(Thread.currentThread().getName() + "\t" + futureTask.get());

    }

}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t hello callable");
        return "hello callable";
    }


}
