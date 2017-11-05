package org.personal.app.framework.ratelimit;

import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.context.RequestContext;

/**
 * Created at: 2017-11-02 00:34
 *
 * @author guojing
 */
public interface IRateLimitService {

    void checkRateLimit(RequestContext rc, RateLimit rateLimit);

}
