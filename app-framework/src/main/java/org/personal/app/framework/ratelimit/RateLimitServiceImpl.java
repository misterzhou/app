package org.personal.app.framework.ratelimit;

import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.context.RequestContext;
import org.springframework.stereotype.Service;

/**
 * Created at: 2017-10-31 09:17
 *
 * @author guojing
 */
@Service
public class RateLimitServiceImpl implements RateLimitService {

    @Override
    public void checkRateLimit(RequestContext rc, String api, RateLimit[] rateLimits, String ip, String uid, String param) {

    }

    @Override
    public void checkRateLimit(RequestContext rc, RateLimit[] rateLimits) {

    }
}
