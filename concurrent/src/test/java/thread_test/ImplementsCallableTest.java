package thread_test;

import thread.ImplementsCallable;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImplementsCallableTest {

    @Test
    public void main() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<String> future =  executorService.submit(new ImplementsCallable());
        System.out.println(future.get());
    }
}
