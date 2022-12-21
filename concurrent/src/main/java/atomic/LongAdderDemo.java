package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
/**利用加法器实现线程安全累加，高并发下效率更高**/
public class LongAdderDemo {

    public static void main(String[] args) throws InterruptedException {
       LongAdder counter = new LongAdder();
       ExecutorService service = Executors.newFixedThreadPool(16);
       for (int i = 0; i < 100; i++) {
           service.submit(new Task(counter));
       }
 
       Thread.sleep(2000);
       System.out.println(counter.sum());
   }
   static class Task implements Runnable {
 
       private final LongAdder counter;
 
       public Task(LongAdder counter) {
           this.counter = counter;
       }
 
       @Override
       public void run() {
           counter.increment();
       }
   }
}
