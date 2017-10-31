package org.personal.app.framework.apiprops;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Created at: 2017-10-29 13:20
 *
 * @author guojing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitConfig {

    /**
     * 单位时间最大请求数
     */
    int value();

    TimeUnit time() default TimeUnit.HOURS;

}
