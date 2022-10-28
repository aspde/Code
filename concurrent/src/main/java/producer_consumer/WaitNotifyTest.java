package producer_consumer;

public class WaitNotifyTest {

    public static void main(String[] args) {
        WaitNotifyBlockingQueue waitNotifyBlockingQueue = new WaitNotifyBlockingQueue(10);
        Producer producer = new Producer(waitNotifyBlockingQueue);
        Consumer consumer = new Consumer(waitNotifyBlockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable{

        private final WaitNotifyBlockingQueue waitNotifyBlockingQueue;

        public Producer(WaitNotifyBlockingQueue waitNotifyBlockingQueue){
            this.waitNotifyBlockingQueue = waitNotifyBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try{
                    waitNotifyBlockingQueue.put(new Object());
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private final WaitNotifyBlockingQueue waitNotifyBlockingQueue;

        public Consumer(WaitNotifyBlockingQueue waitNotifyBlockingQueue){
            this.waitNotifyBlockingQueue = waitNotifyBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try{
                    waitNotifyBlockingQueue.take();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}


