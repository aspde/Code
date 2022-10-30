package print_ABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockImpl {

    private static final ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition printACondition = reentrantLock.newCondition();
        Condition printBCondition = reentrantLock.newCondition();
        Condition printCCondition = reentrantLock.newCondition();

        Thread thread1 = new Thread(new PrintRunner(reentrantLock, printACondition, printBCondition, 34, 'A'));
        Thread thread2 = new Thread(new PrintRunner(reentrantLock, printBCondition, printCCondition, 33, 'B'));
        Thread thread3 = new Thread(new PrintRunner(reentrantLock, printCCondition, printACondition, 33, 'C'));

        thread1.start();
        Thread.sleep(100);
        thread2.start();
        thread3.start();
    }

    static class PrintRunner implements Runnable {

        private final ReentrantLock reentrantLock;

        private final Condition curCondition;

        private final Condition nextCondition;

        private final Integer count;

        private final Character character;

        private Integer index = 1;

        public PrintRunner(ReentrantLock reentrantLock, Condition curCondition, Condition nextCondition, Integer count, Character character) {
            this.reentrantLock = reentrantLock;
            this.curCondition = curCondition;
            this.nextCondition = nextCondition;
            this.count = count;
            this.character = character;
        }

        @Override
        public void run() {
            while(true) {
                reentrantLock.lock();
                if(index <= count) {
                    System.out.println(character);
                    index++;
                }
                nextCondition.signal();
                try {
                    curCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }

}
