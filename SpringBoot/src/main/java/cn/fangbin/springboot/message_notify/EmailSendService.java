package cn.fangbin.springboot.message_notify;

import org.springframework.stereotype.Service;

/**
 * Email发送方式实现
 */
@Service("email")
public class EmailSendService implements SendMsgService {
    @Override
    public void sendMsg(String content) {
        System.out.println("邮件发送:" + content);
    }
}
