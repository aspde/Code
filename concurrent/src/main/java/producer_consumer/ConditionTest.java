package producer_consumer;

public class ConditionTest {

    public static void main(String[] args) {
        ConditionBlockingQueue conditionBlockingQueue = new ConditionBlockingQueue(10);
        ConditionTest.Producer producer = new ConditionTest.Producer(conditionBlockingQueue);
        ConditionTest.Consumer consumer = new ConditionTest.Consumer(conditionBlockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable{

        private final ConditionBlockingQueue conditionBlockingQueue;

        public Producer(ConditionBlockingQueue conditionBlockingQueue){
            this.conditionBlockingQueue = conditionBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try{
                    conditionBlockingQueue.put(new Object());
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        private final ConditionBlockingQueue conditionBlockingQueue;

        public Consumer(ConditionBlockingQueue conditionBlockingQueue){
            this.conditionBlockingQueue = conditionBlockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try{
                    conditionBlockingQueue.take();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
