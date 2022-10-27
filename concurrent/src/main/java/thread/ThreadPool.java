package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 通过线程池创建线程
 */
public class ThreadPool {

    public void main() {
        ExecutorService executorService  = Executors.newFixedThreadPool(10);
        executorService.execute(() -> System.out.println("线程池创建线程"));
    }
}
