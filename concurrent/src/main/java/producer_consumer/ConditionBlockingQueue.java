package producer_consumer;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 Condition 实现生产者消费者模式
 */
public class ConditionBlockingQueue {

    private final Queue queue;

    private int max;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    public ConditionBlockingQueue(Queue queue, int max){
        this.queue = queue;
        this.max = max;
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try{
            //因为生产者消费者往往是多线程的，使用while，避免了获取的数据为 null 或抛出异常的情况。
            while(queue.size() == max){
                notFull.await();
            }
            queue.add(o);
            notEmpty.signalAll();
        } finally {
            //把 unlock 方法放在 finally 中是一个基本原则，否则可能会产生无法释放锁的情况。
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while(queue.size() == 0){
                notEmpty.await();
            }
            Object item = queue.remove();
            notFull.signalAll();
            return item;
        } finally {
            lock.unlock();
        }
    }

}
