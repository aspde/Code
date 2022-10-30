package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试公平锁和非公平锁特性
 */
public class FairAndUnfair {


    public static void main(String[] args) {

        PrintQueue printQueue = new PrintQueue();

        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) thread[i] = new Thread(new Job(printQueue), "Thread " + i);


        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Job implements Runnable {

        private final PrintQueue printQueue;

        Job(PrintQueue printQueue) {
            this.printQueue = printQueue;
        }

        @Override
        public void run() {
            System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());
            printQueue.printJob();
            System.out.printf("%s: The document has been printed\n", Thread.currentThread().getName());
        }

    }


    static class PrintQueue {

        private final Lock queueLock = new ReentrantLock(false);

        void printJob() {
            queueLock.lock();

            try {
                long duration = (long) (Math.random() * 10000);
                System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                        Thread.currentThread().getName(), (duration / 1000));
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }

            queueLock.lock();
            try {
                long duration = (long) (Math.random() * 10000);
                System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
                        Thread.currentThread().getName(), (duration / 1000));
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }

}