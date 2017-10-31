package org.personal.app.framework.auth;

import org.personal.app.commons.auth.AuthGrantType;
import org.personal.app.commons.auth.Token;

/**
 * Created at: 2017-10-29 10:47
 *
 * @author guojing
 */
public class AuthResource {

    private AuthGrantType grantType;
    private Token token;
    private String uid;
    private String udid;

    public AuthResource() {}

    public AuthResource(AuthGrantType grantType, Token token, String uid) {
        this.grantType = grantType;
        this.token = token;
        this.uid = uid;
    }

    public AuthResource(AuthGrantType grantType, Token token, String uid, String udid) {
        this(grantType, token, uid);
        this.udid = udid;
    }

    public AuthGrantType getGrantType() {
        return grantType;
    }

    public AuthResource setGrantType(AuthGrantType grantType) {
        this.grantType = grantType;
        return this;
    }

    public Token getToken() {
        return token;
    }

    public AuthResource setToken(Token token) {
        this.token = token;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public AuthResource setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getUdid() {
        return udid;
    }

    public AuthResource setUdid(String udid) {
        this.udid = udid;
        return this;
    }

}
