package org.personal.app.framework.apiprops;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created at: 2017-10-29 13:20
 *
 * @author guojing
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitConfig {

    /**
     * 限制类型
     */
    RateLimitType type();
    /**
     * 单位时间的限制次数配置
     */
    LimitTimesConfig[] rateTimes();

}
