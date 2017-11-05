package org.personal.app.framework.ratelimit;

import org.personal.app.framework.apiprops.LimitTimesConfig;
import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.apiprops.RateLimitConfig;
import org.personal.app.framework.apiprops.RateLimitType;
import org.personal.app.framework.context.RequestContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created at: 2017-10-31 09:17
 *
 * @author guojing
 */
@Service
public class RateLimitServiceImpl implements IRateLimitService {

    private static final String RATE_LIMIT_PREFIX_KEY = "rl";
    private static final String KEY_SPLIT = "_";

    @Resource
    RateLimitRedisDao redisDao;

    @Override
    public void checkRateLimit(RequestContext rc, RateLimit rateLimit) {
        RateLimitConfig[] configs = rateLimit.configs();
        if (configs.length == 0) {
            return;
        }

        String api = rc.getAppRequest().getRequestAPI();
        String uid = rc.getCurrentUid();
        String ip = rc.getIp();
        String param = null;
        for (RateLimitConfig config : configs) {
            RateLimitType type = config.type();
            LimitTimesConfig[] limitTimeConfigs = config.rateTimes();
            for (LimitTimesConfig timesConfig : limitTimeConfigs) {
                TimeUnit timeUnit = timesConfig.timeUnit();
                String key = getKey(api, type, getTypeValue(type, uid, ip, param), timeUnit);
                int currentCount = redisDao.incrCount(key, timeUnit);
                if (currentCount > timesConfig.value()) {
                    throw RateLimitException.newRequestOverLimit(type);
                }
            }
        }
    }

    private String getKey(String api, RateLimitType type, String typeValue, TimeUnit timeUnit) {
        StringBuilder buf = new StringBuilder(api.length() + type.name().length() + timeUnit.name().length() + 20);
        buf.append(RATE_LIMIT_PREFIX_KEY).append(KEY_SPLIT);
        buf.append(api).append(KEY_SPLIT);
        buf.append(type.name().toLowerCase()).append(':').append(typeValue).append(KEY_SPLIT);
        buf.append(timeUnit.name().toLowerCase()).append(':').append(getCurrentTime(timeUnit)).append(KEY_SPLIT);
        return buf.toString();
    }

    private String getTypeValue(RateLimitType type, String uid, String ip, String param) {
        String result = null;
        switch (type) {
            case IP:
                result = ip;
                break;
            case USER:
                result = uid;
                break;
            case PARAM:
                throw new IllegalArgumentException("当前不支持Param方式进行频次限制");
        }
        return result;
    }

    private int getCurrentTime(TimeUnit timeUnit) {
        Calendar calendar = Calendar.getInstance();
        int result;
        switch (timeUnit) {
            case DAYS:
                result = calendar.get(Calendar.DAY_OF_MONTH);
                break;
            case HOURS:
                result = calendar.get(Calendar.HOUR_OF_DAY);
                break;
            case MINUTES:
                result = calendar.get(Calendar.MINUTE);
                break;
            default:
                throw new IllegalArgumentException("LimitTimesConfig.timeUnit only accept MINUTES,HOURS,DAYS");
        }
        return result;
    }

}
