package cn.fangbin.springboot.aop_log;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class OperateAspect {

    /**
     * 定义切入点
     * 横切逻辑
     * 织入
     */
    @Pointcut("@annotation(cn.fangbin.springboot.aop_log.RecordOperate)")
    public void pointcut() {}

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1, 1, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(() -> {
            try {
                MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
                RecordOperate annotation = methodSignature.getMethod().getAnnotation(RecordOperate.class);

                Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                OperateLogDO operateLogDO = logConvert.convert(proceedingJoinPoint.getArgs()[0]);

                operateLogDO.setDesc(annotation.desc());
                operateLogDO.setResult(result.toString());

                System.out.println("insert operateLog" + JSON.toJSONString(operateLogDO));
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }
}
