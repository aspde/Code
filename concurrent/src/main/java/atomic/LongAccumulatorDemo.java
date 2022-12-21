package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;
/**利用积累器实现线程安全累加**/
public class LongAccumulatorDemo {
    // 为什么不用for循环？
    // for循环执行的时候是串行，一定是按照0+1+2+3+...+8+9这样的顺序相加的，但是LongAccumulator的一大优势就是可以利用线程池来为它工作。
    // 一旦使用了线程池，那么多个线程之间是可以并行计算的，效率要比之前的串行高得多。
    // 这也是为什么说它加的顺序是不固定的，因为并不能保证各个线程之间的执行顺序，所能保证的就是最终的结果是确定的。
    public static void main(String[] args) throws InterruptedException {
        // 加的顺序是不固定的，并不是说会按照顺序从1开始逐步往上累加，但由于加法有交换律，所以最终加出来的结果是不变的
        LongAccumulator accumulator = new LongAccumulator(Long::sum, 0);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        // 利用整形流往线程池中提交了从1~9这9个任务
        IntStream.range(1, 10).forEach(i -> executor.submit(() -> accumulator.accumulate(i)));
        // 等待线程池的任务执行完毕
        Thread.sleep(2000);
        System.out.println(accumulator.getThenReset());
    }
}
