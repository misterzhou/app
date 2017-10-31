package org.personal.app.commons.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * Created at: 2017-10-26 22:42
 *
 * @author guojing
 */
public enum AuthGrantType {

    /**
     * 客户端token类型
     */
    client_token((byte)1, 30),
    /**
     * 服务端token类型
     */
    server_token((byte)2, 30);

    public static final long START_TIMESTAMP = 1509029402000L;
    private static final Map<Byte, AuthGrantType> valueMaps = new HashMap<>();

    static {
        for (AuthGrantType type : AuthGrantType.values()) {
            valueMaps.put(type.value, type);
        }
    }

    private final byte value;
    /**
     * token有效期，单位：天
     */
    private final int expireDay;

    AuthGrantType(byte value, int expireDay) {
        this.value = value;
        this.expireDay = expireDay;
    }

    public byte getValue() {
        return value;
    }

    public static AuthGrantType parse(byte value) {
        return valueMaps.get(value);
    }

    /**
     * 获取剩余过期时间，毫秒数
     * @param time
     * @return
     */
    public long getRemainExpiredTime(long time) {
        return time + START_TIMESTAMP + getExpiredTime() - System.currentTimeMillis();
    }

    /**
     * 获取过期时间的毫秒值
     * @return
     */
    public long getExpiredTime() {
        return expireDay * 1000L * 60L * 60L * 24L;
    }

}
