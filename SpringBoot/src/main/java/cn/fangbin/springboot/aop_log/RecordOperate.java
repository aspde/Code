package cn.fangbin.springboot.aop_log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RecordOperate {
    String desc() default "";

    Class<? extends Convert> convert();
}
