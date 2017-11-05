package org.personal.app.framework.auth;

import org.personal.app.commons.auth.Token;
import org.personal.app.framework.request.AppRequest;

/**
 * Created at: 2017-10-29 10:44
 *
 * @author guojing
 */
public interface AuthenticationProvider {

    AuthResource verifyToken(AppRequest request);

    AuthResource verifyToken(AuthenticationType type, String tokenStr);

    Token refreshToken(AuthenticationType type, Token token);

    Token refreshToken(AuthenticationType type, String tokenStr);

    void checkToken(AuthenticationType type, Token token);

}
