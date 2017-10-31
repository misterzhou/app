package org.personal.app.commons.auth;

/**
 * Created at: 2017-10-27 08:33
 *
 * @author guojing
 */
public enum  AccessTokenVersion {

    v1(1, "fCjiF6I40CbYc4I4ZZ8iCw==");

    public final static AccessTokenVersion currentVersion = v1;
    private int version;
    private String secret;

    AccessTokenVersion(int version, String secret) {
        this.version = version;
        this.secret = secret;
    }

    public static AccessTokenVersion valueOf(int version) {
        for (AccessTokenVersion ver : AccessTokenVersion.values()) {
            if (ver.version == version) {
                return ver;
            }
        }
        return null;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

}
