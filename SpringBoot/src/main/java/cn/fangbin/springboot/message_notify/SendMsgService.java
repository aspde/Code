package cn.fangbin.springboot.message_notify;

/**
 * 发送消息的能力抽象，可以理解为策略接口
 */
public interface SendMsgService {

    // 发送内容
    void  sendMsg(String content);
}
