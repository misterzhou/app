package org.personal.app.ratelimit.service;

import org.personal.app.framework.apiprops.LimitTimesConfig;
import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.apiprops.RateLimitConfig;
import org.personal.app.framework.apiprops.RateLimitType;
import org.personal.app.framework.context.RequestContext;
import org.personal.app.ratelimit.api.IRateLimitService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created at: 2017-10-31 09:17
 *
 * @author guojing
 */
@Service
public class RateLimitServiceImpl implements IRateLimitService {

    @Override
    public void checkRateLimit(RequestContext rc, RateLimit rateLimit) {
        RateLimitConfig[] configs = rateLimit.configs();
        if (configs.length == 0) {
            return;
        }

        String api = rc.getAppRequest().getRequestAPI();
        for (RateLimitConfig config : configs) {
            RateLimitType type = config.type();
            LimitTimesConfig[] limitTimeConfigs = config.rateTimes();
            for (LimitTimesConfig timesConfig : limitTimeConfigs) {
                TimeUnit timeUnit = timesConfig.timeUnit();
                int count = timesConfig.value();

            }
        }
    }

    private void checkRateLimit(String api, String uid, String ip) {

    }
}
