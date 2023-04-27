package cn.fangbin.springboot.aop_log;

public class SaveOrderConvert implements Convert<SaveOrder> {
    @Override
    public OperateLogDO convert(SaveOrder saveOrder) {
        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(saveOrder.getId());
        return operateLogDO;
    }
}
