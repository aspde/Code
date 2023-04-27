package cn.fangbin.springboot.aop_log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateLogDO {
    private Long orderId;

    private String desc;

    private String result;
}
