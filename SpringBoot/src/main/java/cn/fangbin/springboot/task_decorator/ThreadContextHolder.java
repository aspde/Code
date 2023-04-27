package cn.fangbin.springboot.task_decorator;

import java.util.Map;

public class ThreadContextHolder {

    private static final ThreadLocal<Map<String, Object>> ctx = new ThreadLocal<>();

    public static Map<String, Object> getContext(){
        return ctx.get();
    }

    public static void setContext(Map<String, Object> attrs){
        ctx.set(attrs);
    }

    public static void removeContext(){
        ctx.remove();
    }
}
