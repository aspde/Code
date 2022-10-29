package lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;
//非常典型的利用锁的降级功能更新缓存
public class CachedData {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
 
    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                
                if (!cacheValid) {
                    data = new Object();
                    cacheValid = true;
                }
                
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock();
            }
        }
 
        try {
            System.out.println(data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}
