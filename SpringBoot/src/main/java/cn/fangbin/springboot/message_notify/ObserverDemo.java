package cn.fangbin.springboot.message_notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * 执行的顺序怎么去定义和实现？
 * 单个通知失败了怎么办？
 * 选不同的组件当配置中心有何区别？
 * 增加一种新的发送渠道怎么办？
 * 如果大批量短时间发送，有哪些地方可以优化？
 */
@SpringBootApplication
public class ObserverDemo implements CommandLineRunner {

    // 这里可以注册多个观察者，这个demo只注册了一个消息通知
    @Autowired
    List<Observer> observers;

    public static void main(String[] args) {
        new SpringApplication(ObserverDemo.class).run(args);
    }

    // 容器启动即执行
    @Override
    public void run(String... args) throws Exception {
        sendMsg("bizType1", "业务1的内容");
        sendMsg("bizType2", "业务2的内容");
    }

    // client调用，指明业务和需要发送的内容
    private void sendMsg(String bizType, String content) {
        observers.forEach(observer -> observer.notify(bizType, content));
    }
}
