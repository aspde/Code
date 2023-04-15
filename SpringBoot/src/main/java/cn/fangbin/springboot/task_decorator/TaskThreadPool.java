package cn.fangbin.springboot.task_decorator;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskThreadPool {

    @Bean("taskExecutor") // bean的名称，默认为首字母小写的方法名
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(20);
        // 设置最大线程数
        executor.setMaxPoolSize(30);
        // 设置队列容量
        executor.setQueueCapacity(1000);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("job-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池，全局线程池不能关闭
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置自定义的Decorator
        executor.setTaskDecorator(new ContextDecorator());
        return executor;
    }
}
