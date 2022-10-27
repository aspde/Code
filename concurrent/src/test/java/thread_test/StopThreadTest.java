package thread_test;

import org.junit.Test;
import thread.StopThread;

public class StopThreadTest {

    @Test
    public void main() throws InterruptedException {
        Thread thread = new Thread(new StopThread());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }
}
