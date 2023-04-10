package merge_queue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperateChangeLog {

    private Long orderId;

    private Integer count;

    // 1-扣减，2-回滚
    private Integer operateType;
}
