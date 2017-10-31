package org.personal.app.framework.controller;

import com.alibaba.fastjson.JSONObject;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created at: 2017-10-21 23:34
 *
 * @author guojing
 */
@RestController
@RequestMapping("/help")
public class HelpController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @BaseInfo(desc = "ping", authType = AuthType.anonymous)
    public JSONObject ping() {
        JSONObject result = new JSONObject();
        result.put("msg", "pong");
        result.put("systemTime", System.currentTimeMillis());
        return result;
    }

}
