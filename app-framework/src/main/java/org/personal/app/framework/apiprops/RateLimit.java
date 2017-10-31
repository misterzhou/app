package org.personal.app.framework.apiprops;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at: 2017-10-29 13:18
 *
 * @author guojing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    /**
     * 限制类型
     */
    RateLimitType type();

    /**
     * 配置
     */
    RateLimitConfig[] configs();

}
