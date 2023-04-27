package cn.fangbin.springboot.task_decorator;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContextDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, Object> headers = new HashMap<>();
        for (String header : Collections.list(request.getHeaderNames())) {
            headers.put(header, request.getHeader(header));
        }

        return () -> {
            try {
                ThreadContextHolder.setContext(headers);
                runnable.run();
            } finally {
                ThreadContextHolder.removeContext();
            }
        };
    }
}