package org.personal.app.user.model;

import org.personal.app.commons.json.AppJSONCodecObject;
import org.personal.app.commons.json.AppJSONObject;

/**
 * Created at: 2017-10-31 22:30
 *
 * @author guojing
 */
public class UserExtend implements AppJSONCodecObject {

    private String uid;
    private String email;

    public UserExtend() {}

    public UserExtend(AppJSONObject jsonObj) {
        initWithJSON(jsonObj);
    }

    public String getUid() {
        return uid;
    }

    public UserExtend setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserExtend setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        this.uid = jsonObject.getString("uid");
        this.email = jsonObject.getString("email");
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject jsonObj = AppJSONObject.newObject();
        jsonObj.put("uid", uid);
        jsonObj.put("email", email);
        return jsonObj;
    }

    @Override
    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
