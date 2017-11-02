package org.personal.app.user;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.personal.app.commons.auth.TokenUtils;
import org.personal.app.framework.apiprops.*;
import org.personal.app.framework.context.RequestContext;
import org.personal.app.user.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

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
    @BaseInfo(desc = "查询用户信息", status = ApiStatus.PUBLIC, authType = AuthType.anonymous, rateLimit = true)
    @RateLimit(configs = {
            @RateLimitConfig(type = RateLimitType.IP, rateTimes = {@LimitTimesConfig(value = 100, timeUnit = TimeUnit.MINUTES)}),
            @RateLimitConfig(type = RateLimitType.USER, rateTimes = {@LimitTimesConfig(value = 100, timeUnit = TimeUnit.MINUTES)})
    })
    public User show(RequestContext rc, @RequestParam(name = "uid") String uid) {
        return new User(uid, "guojing");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @BaseInfo(desc = "更新用户信息", status = ApiStatus.PUBLIC, authType = AuthType.anonymous)
    public User update(RequestContext rc, @RequestBody @Valid User user) {
        return user;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @BaseInfo(desc = "test", status = ApiStatus.PUBLIC, authType = AuthType.anonymous)
    public JSONObject test(RequestContext rc, @RequestParam @Length(min = 5, max = 10) String uid, @RequestParam(defaultValue = "0") @Range(min = 0, max = 50) int age) {
        JSONObject result = new JSONObject();
        result.put("uid", uid);
        result.put("age", age);
        return result;
    }

}
