package create_thread;

/**
 * 通过实现Runnable接口创建线程
 */
public class ImplementsRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("实现Runnable接口创建线程");
    }
}
