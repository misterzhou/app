package org.personal.app.framework.apiprops;

import org.apache.commons.lang.ArrayUtils;
import org.personal.app.commons.auth.AuthGrantType;

/**
 * Created at: 2017-10-22 11:32
 *
 * 接口认证类型
 *
 * @author guojing
 */
public enum AuthType {

    /**
     * 不需要认证
     */
    anonymous(),
    client(AuthGrantType.client_token),
    server(AuthGrantType.server_token),
    all(AuthGrantType.client_token, AuthGrantType.server_token);

    private final AuthGrantType[] grantTypes;

    AuthType(AuthGrantType... grantTypes){
        this.grantTypes = grantTypes;
    }

    public boolean support(AuthGrantType grantType) {
        if (this.grantTypes == null ||this.grantTypes.length == 0) {
            return true;
        }
        for (AuthGrantType type : this.grantTypes) {
            if (type == grantType) {
                return true;
            }
        }

        return false;
    }

    public boolean needAuth() {
        return ArrayUtils.isNotEmpty(grantTypes);
    }

}
