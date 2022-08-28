package create_thread;

/**
 * 通过继承Thread类创建线程
 */
public class ExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread类创建线程");
    }
}
