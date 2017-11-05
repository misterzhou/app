package org.personal.app.auth;

import org.personal.app.commons.AppException;
import org.personal.app.commons.auth.*;
import org.personal.app.framework.auth.AuthManager;
import org.personal.app.framework.auth.AuthResource;
import org.personal.app.framework.auth.AuthenticationType;
import org.springframework.stereotype.Service;

/**
 * Created at: 2017-10-29 11:12
 *
 * @author guojing
 */
@Service
public class AppAuthManager implements AuthManager {

    @Override
    public AuthenticationType getAuthenticationType() {
        return AuthenticationType.APP_AUTH;
    }

    @Override
    public boolean support(AuthenticationType type) {
        return type == this.getAuthenticationType();
    }

    @Override
    public AuthResource verifyToken(String tokenStr) {
        Token token = TokenUtils.checkToken(tokenStr);
        if (token == null) {
            return null;
        }

        AuthResource result = null;
        switch (token.getGrantType()) {
            case client_token:
                ClientToken clientToken = (ClientToken) token;
                result = new AuthResource(clientToken.getGrantType(), clientToken, clientToken.getUid(), clientToken.getUdid());
                break;
            case server_token:
                result = new AuthResource(token.getGrantType(), token, token.getUid());
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public Token refreshToken(Token token) {
        checkToken(token);
        if (token.getRemainExpiredTime() < token.getGrantType().getExpiredTime() / 3) {
            token.setToken(TokenUtils.refreshToken(token));
            token.refreshRemainExpiredTime();
        }
        return token;
    }

    @Override
    public Token refreshToken(String tokenStr) {
        Token token = TokenUtils.checkToken(tokenStr);
        return refreshToken(token);
    }

    @Override
    public void checkToken(Token token) {
        if (token == null) {
            throw AppException.newInvalidTokenException();
        }
        if (token.getRemainExpiredTime() < 5000L) {
            throw AppException.newTokenExpiresException();
        }
    }
}
