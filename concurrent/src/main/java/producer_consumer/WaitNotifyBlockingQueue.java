package producer_consumer;

import java.util.LinkedList;

/**
 * 用 wait/notify 实现生产者消费者模式
 */
public class WaitNotifyBlockingQueue {

    private int maxSize;

    private LinkedList<Object> storage;

    public WaitNotifyBlockingQueue(int maxSize){
        this.maxSize = maxSize;
        storage = new LinkedList<Object>();
    }

    public synchronized void put() throws InterruptedException {
        while(storage.size() == maxSize){
            wait();
        }
        storage.add(new Object());
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
