package print_ABC;

public class WaitNotifyImpl {

    private static Integer state = 0;

    private static final Object lock = new Object();

    private static Integer count = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if(state == 0 && count + 1 <= 100){
                        System.out.println("A");
                        lock.notify();
                        state = 1;
                        count++;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if(state == 1 && count + 1 <= 100){
                        System.out.println("B");
                        lock.notify();
                        state = 2;
                        count++;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    if(state == 2 && count + 1 <= 100){
                        System.out.println("C");
                        lock.notify();
                        state = 0;
                        count++;
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
