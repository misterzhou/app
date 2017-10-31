package org.personal.app.commons;

/**
 * Created at: 2017-10-22 15:42
 *
 * @author guojing
 */
public enum ErrorCode {

    //server error
    INTERNAL_SERVER_ERROR(1001, HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(1002, HttpStatus.SERVICE_UNAVAILABLE),


    //client error
    BAD_REQUEST(2001, HttpStatus.BAD_REQUEST),
    API_NOT_FOUND(2002, HttpStatus.NOT_FOUND),
    UNSUPPORTED_MEDIA_TYPE(2003, HttpStatus.UNSUPPORTED_MEDIA_TYPE),
    FORBIDDEN(2004, HttpStatus.FORBIDDEN),
    REQUEST_NOT_ACCEPTABLE(2005, HttpStatus.NOT_ACCEPTABLE),
    REQUESTED_RANGE_NOT_SATISFIABLE(2006, HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE),


    //business error
    //业务逻辑错误 http状态都返回 200


    /**
     * 参数错误 接口参数校验错误都使用这个错误码,客户端遇到本错误直接提示给用户。
     */
    PARAM_ERROR(3000, HttpStatus.BAD_REQUEST),
    /**
     * 对象不存在错误 具体对象类型使用参数指定
     */
    ENTITY_NOT_EXIST(3001, HttpStatus.OK),
    /**
     * 非法状态 对象状态不允许此项操2
     */
    ILLEGAL_STATUS(3002, HttpStatus.OK),
    /**
     * 重复操作 对象已经处于操作的目标状态
     */
    REPEAT_OPERATION(3003, HttpStatus.OK),
    /**
     * 重复的唯一键 创建对象的过程中违反了唯一键的约束
     */
    DUPLICATE_UNIQ_KEY(3004, HttpStatus.OK),
    /**
     * 没有权限
     */
    NO_PERMISSION(3005, HttpStatus.OK),

    /**
     * Beta 功能访问限制
     */
    BETA_FEATURE_ACCESS_LIMIT(3006, HttpStatus.OK),

    AUTH_FAIL(3007, HttpStatus.OK),

    /**
     * 本地缓存过期 需要重新获取
     */
    LOCAL_CACHE_EXPIRES(4001, HttpStatus.OK);

    private int code;
    private HttpStatus httpStatus;

    ErrorCode(int code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public static ErrorCode getByCode(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode;
            }
        }
        return ErrorCode.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
