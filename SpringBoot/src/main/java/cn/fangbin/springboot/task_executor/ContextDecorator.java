package cn.fangbin.springboot.task_executor;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ContextDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            // 获取父线程的request的user-agent(示例)
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ua = request.getHeader("user-agent");
            return () -> {
                try {
                    // 将父线程的ua设置进子线程里
                    ThreadLocalData.setUa(ua);
                    // 子线程方法执行
                    runnable.run();
                } finally {
                    // 清除父线程threadLocal的值
                    ThreadLocalData.remove();
                }
            };
        } catch (IllegalStateException e) {
            return runnable;
        }
    }
}
