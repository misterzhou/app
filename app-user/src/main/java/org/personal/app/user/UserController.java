package org.personal.app.user;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.personal.app.commons.auth.TokenUtils;
import org.personal.app.framework.apiprops.ApiStatus;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.personal.app.framework.context.RequestContext;
import org.personal.app.user.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * Created at: 2017-10-28 21:32
 *
 * @author guojing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @BaseInfo(desc = "用户登录", status = ApiStatus.PUBLIC, authType = AuthType.anonymous)
    public JSONObject login(RequestContext rc, @RequestParam String username, @RequestParam String password) {
        String uid = username + RandomStringUtils.randomNumeric(5);
        JSONObject result = new JSONObject();
        result.put("uid", uid);
        result.put("username", username);
        result.put("token", TokenUtils.generateClientToken(uid, rc.getUdid()));
        return result;
    }

    @RequestMapping(value = "/show", method = RequestMethod.POST)
    @BaseInfo(desc = "查询用户信息", status = ApiStatus.PUBLIC, authType = AuthType.anonymous)
    public User show(RequestContext rc, @RequestParam(name = "uid") String uid) {
        return new User(uid, "guojing");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BaseInfo(desc = "更新用户信息", status = ApiStatus.PUBLIC, authType = AuthType.all)
    public User update(RequestContext rc, @RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @BaseInfo(desc = "更新用户信息", status = ApiStatus.PUBLIC, authType = AuthType.anonymous)
    public JSONObject test(RequestContext rc, @RequestParam String uid, @RequestParam(defaultValue = "-1") int age) {
        JSONObject result = new JSONObject();
        result.put("uid", uid);
        result.put("age", age);
        return result;
    }

}
