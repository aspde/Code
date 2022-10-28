package producer_consumer;

import java.util.LinkedList;

/**
 * 用 wait/notify 实现生产者消费者模式
 */
public class WaitNotifyBlockingQueue {

    private final int maxSize;

    private final LinkedList<Object> storage;

    public WaitNotifyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
        storage = new LinkedList<>();
    }

    public synchronized void put(Object o) throws InterruptedException {
        while(storage.size() == maxSize){
            wait();
        }
        storage.add(o);
        notifyAll();
    }

    public synchronized void take() throws InterruptedException {
        while(storage.size() == 0){
            wait();
        }
        storage.remove();
        notifyAll();
    }

}
