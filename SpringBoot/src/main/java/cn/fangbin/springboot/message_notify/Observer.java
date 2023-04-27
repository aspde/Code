package cn.fangbin.springboot.message_notify;

/**
 * 观察者，JDK自带有观察者，定义接口不指定实现
 */
public interface Observer {
    void notify(String bizType, String content);
}
