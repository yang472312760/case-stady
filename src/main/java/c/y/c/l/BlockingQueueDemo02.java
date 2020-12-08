package c.y.c.l;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo02 {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        //offer向队列插入数据，如果插入数据超出队列大小，将返回false
        System.out.println(blockingQueue.offer("d"));

        //检查对首是否存在值
        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        //如果移除数据超出队列大小将返回null
        System.out.println(blockingQueue.poll());


    }

}
