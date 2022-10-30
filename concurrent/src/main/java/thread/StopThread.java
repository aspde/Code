package thread;

/**
 * 通过interrupt方法停止线程
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while(!Thread.currentThread().isInterrupted() && count<1000 ){
            System.out.println("count=" + count++);
        }
    }
}
