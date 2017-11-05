package org.personal.app.framework.ratelimit;

import org.personal.app.commons.AppException;
import org.personal.app.commons.ErrorCode;
import org.personal.app.framework.apiprops.RateLimitType;

/**
 * Created at: 2017-11-05 22:51
 *
 * @author guojing
 */
public class RateLimitException {

    public static AppException newRequestOverLimit(RateLimitType type) {
        switch (type) {
            case IP:
                throw newIPRequestOverLimit();
            case USER:
                throw newUserRequestOverLimit();
            case PARAM:
                throw newParamRequestOverLimit();
            default:
                throw newRequestOverLimit("接口请求次数超过限制");
        }
    }

    public static AppException newUserRequestOverLimit() {
        throw newRequestOverLimit("用户请求频次超过限制");
    }

    public static AppException newIPRequestOverLimit() {
        throw newRequestOverLimit("IP请求频次超过限制");
    }

    public static AppException newParamRequestOverLimit() {
        throw newRequestOverLimit("参数请求频次超过限制");
    }

    public static AppException newRequestOverLimit(String errorMsg) {
        throw new AppException(ErrorCode.FORBIDDEN, errorMsg);
    }

}
