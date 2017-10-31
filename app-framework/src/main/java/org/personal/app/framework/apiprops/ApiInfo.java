package org.personal.app.framework.apiprops;

import org.personal.app.commons.AppException;
import org.personal.app.commons.json.AppJSONCodecObject;
import org.personal.app.commons.json.AppJSONObject;

/**
 * Created at: 2017-10-29 13:09
 *
 * @author guojing
 */
public class ApiInfo implements AppJSONCodecObject {

    BaseInfo baseInfo;

    Param[] paramInfo;

    public ApiInfo() {}

    public ApiInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public ApiInfo setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
        return this;
    }

    public Param[] getParamInfo() {
        return paramInfo;
    }

    public ApiInfo setParamInfo(Param[] paramInfo) {
        this.paramInfo = paramInfo;
        return this;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        throw AppException.newUnsupportedOperation();
    }

    @Override
    public AppJSONObject toJSONObject() {
        return null;
    }

    @Override
    public String toJSONString() {
        return null;
    }
}
