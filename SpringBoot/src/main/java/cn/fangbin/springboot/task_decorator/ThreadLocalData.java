package cn.fangbin.springboot.task_decorator;

public class ThreadLocalData {

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getUa(){
        return threadLocal.get();
    }

    public static void setUa(String ua){
        threadLocal.set(ua);
    }

    public static void remove(){
        threadLocal.remove();
    }
}
