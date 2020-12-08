package c.y.c.l;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo03 {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        //当使用put方法时，如果添加元素超过队列大小，将阻塞
        //blockingQueue.put("a");

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        //当使用take方法时，如果移除元素超过队列大小，将阻塞
        //blockingQueue.take();
    }

}
