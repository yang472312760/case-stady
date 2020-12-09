package c.y.c.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue：由数组结构组成的有界阻塞队列
 */
public class BlockingQueueDemo01 {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);


        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));
        //如果添加元素超过队列初始化大小报如下异常
        /*
        Exception in thread "main" java.lang.IllegalStateException: Queue full
         */



        //检查队列是否存在元素
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());
        //如果移除元素超过初始化队列大小报如下异常
        /*
        Exception in thread "main" java.util.NoSuchElementException
         */

    }


}
