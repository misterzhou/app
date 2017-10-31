package org.personal.app.framework.auth;

import org.personal.app.commons.auth.Token;

/**
 * Created at: 2017-10-29 11:10
 *
 * @author guojing
 */
public interface AuthManager {

    AuthenticationType getAuthenticationType();

    boolean support(AuthenticationType type);

    AuthResource verifyToken(String tokenStr);

    Token refreshToken(Token token);

    Token refreshToken(String tokenStr);

}
