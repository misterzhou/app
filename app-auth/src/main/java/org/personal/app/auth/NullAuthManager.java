package org.personal.app.auth;

import org.personal.app.commons.AppException;
import org.personal.app.commons.auth.Token;
import org.personal.app.framework.auth.AuthManager;
import org.personal.app.framework.auth.AuthResource;
import org.personal.app.framework.auth.AuthenticationType;
import org.springframework.stereotype.Service;

/**
 * Created at: 2017-10-31 23:19
 *
 * @author guojing
 */
@Service
public class NullAuthManager implements AuthManager {

    @Override
    public AuthenticationType getAuthenticationType() {
        return AuthenticationType.NULL_AUTH;
    }

    @Override
    public boolean support(AuthenticationType type) {
        return this.getAuthenticationType() == type;
    }

    @Override
    public AuthResource verifyToken(String tokenStr) {
        return null;
    }

    @Override
    public Token refreshToken(Token token) {
        return null;
    }

    @Override
    public Token refreshToken(String tokenStr) {
        return null;
    }

    @Override
    public void checkToken(Token token) {
        throw AppException.newInvalidTokenException();
    }
}
