package org.personal.app.framework.auth;

import org.personal.app.commons.auth.Token;

import javax.servlet.http.HttpServletRequest;

/**
 * Created at: 2017-10-29 10:44
 *
 * @author guojing
 */
public interface AuthenticationProvider {

    AuthResource verifyToken(HttpServletRequest request);

    AuthResource verifyToken(AuthenticationType type, String tokenStr);

    Token refreshToken(AuthenticationType type, Token token);

}
