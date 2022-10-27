package thread;

import java.util.concurrent.Callable;

/**
 * 通过实现Callable接口创建线程
 */
public class ImplementsCallable implements Callable<String> {

    @Override
    public String call(){
        return "实现Callable接口创建线程";
    }
}
