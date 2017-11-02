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

    ApiStatus status() default ApiStatus.PUBLIC;

    AuthType authType() default AuthType.all;

    /**
     * 是否启用接口频次限制
     */
    boolean rateLimit() default false;

    boolean needSSL() default false;

    boolean deprecated() default false;

}
