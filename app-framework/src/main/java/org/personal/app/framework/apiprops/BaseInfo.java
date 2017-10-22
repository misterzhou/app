package org.personal.app.framework.apiprops;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created at: 2017-10-22 11:25
 *
 * 接口属性信息
 *
 * @author guojing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseInfo {

    String desc() default "";

    ApiStatus status();

    AuthType authType() default AuthType.REQUIRED;

    /**
     * 接口频次限制
     */
    RateLimitType[] rateLimit() default {};

    boolean needSSL() default false;

    boolean deprecated() default false;

}
