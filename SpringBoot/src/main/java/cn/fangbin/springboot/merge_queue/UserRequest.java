package cn.fangbin.springboot.merge_queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long orderId;

    private Long userId;

    private Integer count;
}
