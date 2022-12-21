package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
/**利用原子类实现线程安全累加**/
public class AtomicLongDemo {

    public static void main(String[] args) throws InterruptedException {
       AtomicLong counter = new AtomicLong(0);
       ExecutorService service = Executors.newFixedThreadPool(16);
       for (int i = 0; i < 100; i++) {
           service.submit(new Task(counter));
       }
 
       Thread.sleep(2000);
       System.out.println(counter.get());
   }
 
   static class Task implements Runnable {
 
       private final AtomicLong counter;
 
       public Task(AtomicLong counter) {
           this.counter = counter;
       }
 
       @Override
       public void run() {
           counter.incrementAndGet();
       }
   }
}
