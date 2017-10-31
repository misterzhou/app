package org.personal.app.commons;

/**
 * Created at: 2017-10-22 21:24
 *
 * @author guojing
 */
public class BaseResult {

    private int code;
    private Object result;

    public BaseResult(Object result) {
        this.code = HttpStatus.OK.getStatusCode();
        this.result = result;
    }

    public BaseResult(int code, Object result) {
        this.code = code;
        this.result = result;
    }

    public BaseResult(AppException ex) {
        this.code = ex.getCode();
        this.result = ex.getMessage();
    }

    public int getCode() {
        return code;
    }

    public BaseResult setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public BaseResult setResult(Object result) {
        this.result = result;
        return this;
    }
}
