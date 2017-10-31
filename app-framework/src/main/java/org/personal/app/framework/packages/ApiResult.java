package org.personal.app.framework.packages;

import org.personal.app.commons.AppException;
import org.personal.app.commons.HttpStatus;
import org.personal.app.commons.json.AppJSONObject;
import org.personal.app.commons.json.AppJSONObjectAware;
import org.personal.app.commons.json.AppJSONStringAware;

/**
 * Created at: 2017-10-22 21:24
 *
 * @author guojing
 */
public class ApiResult implements AppJSONObjectAware, AppJSONStringAware {

    private int code;
    private Object result;

    public ApiResult() {}

    public ApiResult(Object result) {
        this.code = HttpStatus.OK.getStatusCode();
        this.result = result;
    }

    public ApiResult(int code, Object result) {
        this.code = code;
        this.result = result;
    }

    public ApiResult(AppException ex) {
        this.code = ex.getCode();
        this.result = ex.getMessage();
    }

    public int getCode() {
        return code;
    }

    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ApiResult setResult(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject jsonObj = AppJSONObject.newObject();
        jsonObj.put("code", code);
        jsonObj.put("result", result);
        return jsonObj;
    }

    @Override
    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
