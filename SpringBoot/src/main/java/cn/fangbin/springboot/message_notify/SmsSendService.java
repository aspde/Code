package cn.fangbin.springboot.message_notify;

import org.springframework.stereotype.Service;

/**
 * Sms发送方式实现
 */
@Service("sms")
public class SmsSendService implements SendMsgService {
    @Override
    public void sendMsg(String content) {
        System.out.println("短信发送:" + content);
    }
}
