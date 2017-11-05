package org.personal.app.auth;

import com.alibaba.fastjson.JSONObject;
import org.personal.app.commons.auth.Token;
import org.personal.app.framework.apiprops.ApiStatus;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.personal.app.framework.auth.AuthResource;
import org.personal.app.framework.auth.AuthenticationProvider;
import org.personal.app.framework.context.RequestContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created at: 2017-10-28 21:39
 *
 * @author guojing
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    AuthenticationProvider authenticationProvider;

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    @BaseInfo(desc = "刷新token", status = ApiStatus.PUBLIC, authType = AuthType.client)
    public JSONObject refreshToken(RequestContext rc) {
        AuthResource authResource = authenticationProvider.verifyToken(rc.getAppRequest());
        Token newToken = authenticationProvider.refreshToken(rc.getAppRequest().getAuthenticationType(), authResource.getToken());
        JSONObject result = new JSONObject();
        result.put("token", newToken.getToken());
        return result;
    }

}
