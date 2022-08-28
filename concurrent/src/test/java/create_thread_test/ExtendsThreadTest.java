package create_thread_test;

import create_thread.ExtendsThread;
import org.junit.Test;

public class ExtendsThreadTest {

    @Test
    public void main() {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
    }
}
