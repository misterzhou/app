package org.personal.app.user.model;

import org.personal.app.commons.json.AppJSONCodecObject;
import org.personal.app.commons.json.AppJSONObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created at: 2017-10-30 21:35
 *
 * @author guojing
 */
public class User implements AppJSONCodecObject {

    @NotNull
    private String uid;
    private String username;
    @NotNull
    @Pattern(regexp = "[0-9]{11}")
    private String phone;
    private UserExtend userExtend;

    public User() {}

    public User(String uid) {
        this.uid = uid;
    }

    public User(String uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public User(AppJSONObject jsonObj) {
        initWithJSON(jsonObj);
    }

    public String getUid() {
        return uid;
    }

    public User setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserExtend getUserExtend() {
        return userExtend;
    }

    public User setUserExtend(UserExtend userExtend) {
        this.userExtend = userExtend;
        return this;
    }

    @Override
    public void initWithJSON(AppJSONObject jsonObject) {
        this.uid = jsonObject.getString("uid");
        this.username = jsonObject.getString("username");
        this.phone = jsonObject.getString("phone");
        if (jsonObject.containsKey("userExtend")) {
            this.userExtend = new UserExtend(jsonObject.getJSONObject("userExtend"));
        }
    }

    @Override
    public AppJSONObject toJSONObject() {
        AppJSONObject jsonObj = AppJSONObject.newObject(true);
        jsonObj.put("uid", uid);
        jsonObj.put("username", username);
        jsonObj.put("phone", phone);
        jsonObj.put("userExtend", userExtend);
        return jsonObj;
    }

    @Override
    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
