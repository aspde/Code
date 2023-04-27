package cn.fangbin.springboot.message_notify;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟配置中心，nacos/zookeeper/redis/mysql等均可
 */
public class ConfigCenter {

    private static Map<String, String> sendMsgConfig = new HashMap<>();

    static {
        sendMsgConfig.put("bizType1", "email,sms");
        sendMsgConfig.put("bizType2", "sms");
    }

    // 根据业务获取配置，新业务，配置key-value即可
    public static List<String> getStrategy(String bizType) {
        return Lists.newArrayList(sendMsgConfig.get(bizType).split(","));
    }
}
