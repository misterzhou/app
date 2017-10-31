package org.personal.app.commons.auth;

import org.personal.app.commons.json.AppJSONObject;

/**
 * Created at: 2017-10-27 08:27
 *
 * @author guojing
 */
public class ServerToken extends Token {

    private String serverID;

    public ServerToken(String uid, String serverID, long time, String token) {
        super(AuthGrantType.server_token, uid, time, token);
        this.serverID = serverID;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        super.initWithJSON(jsonObject);
        this.serverID = jsonObject.getString("serverID");
    }

    public String getServerID() {
        return serverID;
    }

    public ServerToken setServerID(String serverID) {
        this.serverID = serverID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ServerToken that = (ServerToken) o;

        return serverID != null ? serverID.equals(that.serverID) : that.serverID == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (serverID != null ? serverID.hashCode() : 0);
        return result;
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject json = super.toJSONObject();
        json.put("serverID", serverID);
        return json;
    }

}
