package org.personal.app.framework.auth;

/**
 * Created at: 2017-10-29 10:50
 *
 * @author guojing
 */
public enum AuthenticationType {

    NULL_AUTH("Null"),
    APP_AUTH("AppAuth");

    private final String value;

    AuthenticationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AuthenticationType parse(String typeName) {
        for (AuthenticationType type : AuthenticationType.values()) {
            if (type.value.equals(typeName)) {
                return type;
            }
        }
        return APP_AUTH;
    }

    public static AuthenticationType parseTypeFromAuthHeader(String authorization) {
        String[] authParts = authorization.split(" ");
        if (authParts.length == 2) {
            return parse(authParts[0]);
        }

        return AuthenticationType.APP_AUTH;
    }

    public static String parseTokenFromAuthHeader(AuthenticationType type, String authorization) {
        String typeHeader = type.getValue() + " ";
        if (authorization.startsWith(typeHeader)) {
            return authorization.substring(typeHeader.length() + 1);
        }

        return authorization;
    }

}
