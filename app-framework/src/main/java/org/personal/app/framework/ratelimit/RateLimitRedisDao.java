package org.personal.app.framework.ratelimit;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created at: 2017-11-05 21:57
 *
 * @author guojing
 */
@Component
public class RateLimitRedisDao {

    @Resource(name = "rateLimitRedis")
    StringRedisTemplate redisTemplate;

    public int incrCount(String key, TimeUnit timeUnit) {
        Long result = redisTemplate.execute((RedisConnection connection) -> {
                Long count = connection.incr(key.getBytes());
                if (count != null && count.longValue() == 1L) {
                    connection.expire(key.getBytes(), timeUnit.toSeconds(1) + 5);
                }
                return count;
        });
        return result != null ? result.intValue() : 0;
    }

}
