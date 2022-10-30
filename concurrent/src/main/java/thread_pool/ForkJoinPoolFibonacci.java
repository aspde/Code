package thread_pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool 计算菲波那切数列
 */
public class ForkJoinPoolFibonacci {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask<Integer> task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
        }
    }

    static class Fibonacci extends RecursiveTask<Integer>{

        int n;

        public Fibonacci(int n){
            this.n = n;
        }

        @Override
        protected Integer compute() {
            if(n <= 1){
                return n;
            }
            Fibonacci fibonacci1 = new Fibonacci(n -1);
            fibonacci1.fork();
            Fibonacci fibonacci2 = new Fibonacci(n -2);
            fibonacci2.fork();
            return fibonacci1.join() + fibonacci2.join();
        }
    }
}
