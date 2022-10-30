package lock;

/**
 * 死锁例子
 */
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {

        LockRunner lockRunner = new LockRunner();

        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(() -> {
            try {
                lockRunner.lock(lockA, lockB);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        lockRunner.lock(lockB, lockA);
    }

    static class LockRunner {

        public void lock(Object owner, Object target) throws InterruptedException {
            synchronized (owner) {
                Thread.sleep(1000);
                synchronized (target) {
                    System.out.println("success");
                }
            }
        }
    }

}
