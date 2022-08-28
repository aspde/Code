package create_thread_test;

import create_thread.ImplementsRunnable;
import org.junit.Test;

public class ImplementsRunnableTest {

    @Test
    public void main() {
        Thread thread = new Thread(new ImplementsRunnable());
        thread.start();
    }
}
