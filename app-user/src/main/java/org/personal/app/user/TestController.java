package org.personal.app.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created at: 2017-10-21 23:34
 *
 * @author guojing
 */
@RestController
@RequestMapping("/help")
public class TestController {

    @RequestMapping("ping")
    public JSONObject ping(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        result.put("msg", "pong");
        result.put("systemTime", System.currentTimeMillis());
        return result;
    }

}
