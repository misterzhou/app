package org.personal.app.commons;

/**
 * Created at: 2017-10-22 15:42
 *
 * @author guojing
 */
public class AppException extends RuntimeException {

    private int code;

    public AppException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public AppException(ErrorCode factor) {
        super(factor.name());
        this.code = factor.getCode();
    }

    public AppException(ErrorCode factor, String msg) {
        super(msg);
        this.code = factor.getCode();
    }

    public AppException(int code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AppException newAppException(ErrorCode errorCode) {
        return new AppException(errorCode);
    }

    public static AppException newAppException(ErrorCode errorCode, String errorMsg) {
        return new AppException(errorCode, errorMsg);
    }

    public static AppException newInvalidTokenException() {
        return new AppException(ErrorCode.AUTH_FAIL, "认证失败，请重试");
    }

    public static AppException newTokenExpiresException() {
        return new AppException(ErrorCode.AUTH_FAIL, "Token以过期，请重新登录");
    }

    public static AppException newUnsupportedOperation() {
        return new AppException(ErrorCode.REQUEST_NOT_ACCEPTABLE, "不支持的操作");
    }

}
