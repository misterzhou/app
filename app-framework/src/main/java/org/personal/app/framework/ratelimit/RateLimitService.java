package org.personal.app.framework.ratelimit;

import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.context.RequestContext;

/**
 * Created at: 2017-10-31 09:15
 *
 * @author guojing
 */
public interface RateLimitService {

    void checkRateLimit(RequestContext rc, String api, RateLimit[] rateLimits, String ip, String uid, String param);

    void checkRateLimit(RequestContext rc, RateLimit[] rateLimits);

}
