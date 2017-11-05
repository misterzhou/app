package org.personal.app.framework.apiprops;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created at: 2017-11-01 23:59
 *
 * @author guojing
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitTimesConfig {

    /**
     * 单位时间最大请求数
     */
    int value();

    /**
     * 支持：DAYS, HOURS, MINUTES
     */
    TimeUnit timeUnit() default TimeUnit.HOURS;

}
