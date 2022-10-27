package producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用 BlockingQueue 实现生产者消费者模式
 */
public class BlockingQueueImplements {

    //ArrayBlockingQueue 已经在背后完成了很多工作，比如队列满了就去阻塞生产者线程，队列有空就去唤醒生产者线程等。
    BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(10);

    public void main() {

        Runnable producer = () -> {
            while(true){
                try {
                    blockingQueue.put(new Object());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(producer);
        new Thread(producer);

        Runnable consumer = () -> {
            while(true){
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        new Thread(consumer);
        new Thread(consumer);
    }

}
