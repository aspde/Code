package thread_test;

import thread.ExtendsThread;
import org.junit.Test;

public class ExtendsThreadTest {

    @Test
    public void main() {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
    }
}
