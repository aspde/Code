package lock;

import java.util.concurrent.atomic.AtomicReference;
//自己实现一个可重入的自旋锁
public class ReentrantSpinLock {

    private final AtomicReference<Thread> owner = new AtomicReference<>();
 
    private int count = 0;
 
    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        
        while (!owner.compareAndSet(null, t)) {
            System.out.println("自旋了");
        }
    }
 
    public void unlock() {
        Thread t = Thread.currentThread();
        
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                owner.set(null);
            }
        }
    }
 
    public static void main(String[] args) {
        ReentrantSpinLock spinLock = new ReentrantSpinLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁");
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了了自旋锁");
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}
