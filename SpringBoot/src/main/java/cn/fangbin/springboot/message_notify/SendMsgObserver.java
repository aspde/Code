package cn.fangbin.springboot.message_notify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送消息的观察者，也实现了发送策略的组合和选择
 */
@Component
public class SendMsgObserver implements Observer {

    // 所有发送实现，均可见
    @Autowired
    List<SendMsgService> sendMsgServices;

    @Override
    public void notify(String bizType, String content) {
        // 获取业务类型 -> 策略组合
        List<String> strategy = ConfigCenter.getStrategy(bizType);
        sendMsgServices.forEach(sendMsgService -> {
            // 配置的策略在内，发送
            if(strategy.contains(sendMsgService.getClass().getDeclaredAnnotation(Service.class).value())) {
                sendMsgService.sendMsg(content);
            }
        });
    }
}
