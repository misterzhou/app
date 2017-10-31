package org.personal.app.commons.auth;

import org.personal.app.commons.json.AppJSONCodecObject;
import org.personal.app.commons.json.AppJSONObject;

/**
 * Created at: 2017-10-26 22:32
 *
 * @author guojing
 */
public abstract class Token implements AppJSONCodecObject {

    protected AuthGrantType grantType;
    protected String uid;
    /**
     * token还有多少毫秒过期
     */
    protected long remainExpiredTime;
    private String token;

    public Token() {}

    public Token(AuthGrantType grantType, String uid, long time) {
        this.grantType = grantType;
        this.uid = uid;
        this.remainExpiredTime = grantType.getRemainExpiredTime(time);
    }

    public Token(AuthGrantType grantType, String uid, long time, String token) {
        this.grantType = grantType;
        this.uid = uid;
        this.remainExpiredTime = grantType.getRemainExpiredTime(time);
        this.token = token;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        this.grantType = AuthGrantType.parse(jsonObject.getByteValue("grantType", AuthGrantType.client_token.getValue()));
        this.uid = jsonObject.getString("uid");
        this.remainExpiredTime = jsonObject.getLongValue("remainExpiredTime", AuthGrantType.client_token.getExpiredTime());
        this.token = jsonObject.getString("token");
    }

    public AuthGrantType getGrantType() {
        return grantType;
    }

    public Token setGrantType(AuthGrantType grantType) {
        this.grantType = grantType;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public Token setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public long getRemainExpiredTime() {
        return remainExpiredTime;
    }

    public Token setRemainExpiredTime(long remainExpiredTime) {
        this.remainExpiredTime = remainExpiredTime;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (remainExpiredTime != token1.remainExpiredTime) return false;
        if (grantType != token1.grantType) return false;
        if (uid != null ? !uid.equals(token1.uid) : token1.uid != null) return false;
        return token != null ? token.equals(token1.token) : token1.token == null;
    }

    @Override
    public int hashCode() {
        int result = grantType != null ? grantType.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (int) (remainExpiredTime ^ (remainExpiredTime >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject json = AppJSONObject.newObject();
        json.put("grantType", grantType);
        json.put("uid", uid);
        json.put("remainExpiredTime", remainExpiredTime);
        json.put("token", token);
        return json;
    }

    @Override
    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
