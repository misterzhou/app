package org.personal.app.commons.auth;

import org.personal.app.commons.json.AppJSONObject;

/**
 * Created at: 2017-10-26 23:44
 *
 * @author guojing
 */
public class ClientToken extends Token {

    /**
     * 设备id
     */
    private String udid;

    public ClientToken() {}

    public ClientToken(String uid, String udid, long time) {
        super(AuthGrantType.client_token, uid, time);
        this.udid = udid;
    }

    public ClientToken(String uid, String udid, long time, String token) {
        super(AuthGrantType.client_token, uid, time, token);
        this.udid = udid;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        super.initWithJSON(jsonObject);
        this.udid = jsonObject.getString("udid");
    }

    public String getUdid() {
        return udid;
    }

    public String getUid() {
        return super.getUid();
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject json = super.toJSONObject();
        json.put("udid", udid);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ClientToken that = (ClientToken) o;

        return udid != null ? udid.equals(that.udid) : that.udid == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (udid != null ? udid.hashCode() : 0);
        return result;
    }
}
