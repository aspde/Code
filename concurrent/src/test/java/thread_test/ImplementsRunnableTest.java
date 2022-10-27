package thread_test;

import thread.ImplementsRunnable;
import org.junit.Test;

public class ImplementsRunnableTest {

    @Test
    public void main() {
        Thread thread = new Thread(new ImplementsRunnable());
        thread.start();
    }
}
