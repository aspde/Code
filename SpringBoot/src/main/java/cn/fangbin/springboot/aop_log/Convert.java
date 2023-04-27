package cn.fangbin.springboot.aop_log;

public interface Convert<PARAM> {
    OperateLogDO convert(PARAM param);
}
