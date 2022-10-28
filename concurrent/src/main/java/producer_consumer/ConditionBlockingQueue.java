package producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用 Condition 实现生产者消费者模式
 */
public class ConditionBlockingQueue {

    private final LinkedList<Object> storage;

    private final int max;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    public ConditionBlockingQueue(int size){
        this.max = size;
        this.storage = new LinkedList<>();
    }

    public void put(Object o) throws InterruptedException {
        lock.lock();
        try{
            //因为生产者消费者往往是多线程的，使用while，避免了获取的数据为 null 或抛出异常的情况。
            while(storage.size() == max){
                notFull.await();
            }
            storage.add(o);
            notEmpty.signalAll();
        } finally {
            //把 unlock 方法放在 finally 中是一个基本原则，否则可能会产生无法释放锁的情况。
            lock.unlock();
        }
    }

    public void take() throws InterruptedException {
        lock.lock();
        try {
            while(storage.size() == 0){
                notEmpty.await();
            }
            storage.remove();
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
