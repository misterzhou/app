package org.personal.app.framework.apiprops;

import org.springframework.web.bind.annotation.ValueConstants;

/**
 * Created at: 2017-10-28 21:52
 *
 * @author guojing
 */
public @interface Param {

    /**
     * 参数说明
     */
    String desc();

    /**
     * The name of the request parameter to bind to
     */
    String name() default "";

    boolean required() ;

    String defaultValue() default ValueConstants.DEFAULT_NONE;

    /**
     * 此参数是否支持频次限制
     */
    boolean enableLimit() default false;

}
