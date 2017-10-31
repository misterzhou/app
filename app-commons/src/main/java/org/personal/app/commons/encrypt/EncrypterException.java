package org.personal.app.commons.encrypt;

/**
 * Created at: 2017-10-26 23:11
 *
 * @author guojing
 */
public class EncrypterException extends RuntimeException {

    public EncrypterException() {
        super();
    }

    public EncrypterException(String message) {
        super(message);
    }

    public EncrypterException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncrypterException(Throwable cause) {
        super(cause);
    }

    protected EncrypterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
