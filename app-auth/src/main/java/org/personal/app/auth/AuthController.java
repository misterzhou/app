package org.personal.app.auth;

import com.alibaba.fastjson.JSONObject;
import org.personal.app.commons.auth.TokenUtils;
import org.personal.app.framework.apiprops.ApiStatus;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.personal.app.framework.context.RequestContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created at: 2017-10-28 21:39
 *
 * @author guojing
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
    @BaseInfo(desc = "查询用户信息", status = ApiStatus.PUBLIC, authType = AuthType.client)
    public JSONObject refreshToken(RequestContext rc) {
        JSONObject result = new JSONObject();
        result.put("uid", rc.getCurrentUid());
        result.put("token", TokenUtils.generateClientToken(rc.getCurrentUid(), rc.getUdid()));
        return result;
    }

}
