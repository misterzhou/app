package org.personal.app.framework.auth;

import org.personal.app.commons.auth.Token;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created at: 2017-10-29 10:59
 *
 * @author guojing
 */
@Service
public class AuthenticationProviderImpl implements AuthenticationProvider, ApplicationContextAware, InitializingBean {

    private static final String PARAM_TOKEN = HttpHeaders.AUTHORIZATION.toLowerCase();

    private ApplicationContext context;
    private Map<AuthenticationType, AuthManager> authManagers;

    @Override
    public AuthResource verifyToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null) {
            authorization = request.getParameter(PARAM_TOKEN);
        }

        AuthResource result = null;
        if (authorization != null) {
            AuthenticationType type = AuthenticationType.parseTypeFromAuthHeader(authorization);
            String tokenStr = AuthenticationType.parseTokenFromAuthHeader(type, authorization);
            result = verifyToken(type, tokenStr);
        }
        if (result == null) {
            result = new AuthResource();
        }
        return result;
    }

    @Override
    public AuthResource verifyToken(AuthenticationType type, String tokenStr) {
        AuthManager authManager = authManagers.get(type);
        if (authManager == null) {
            return null;
        }

        return authManager.verifyToken(tokenStr);
    }

    @Override
    public Token refreshToken(AuthenticationType type, Token token) {
        AuthManager authManager = authManagers.get(type);
        if (authManager == null) {
            return null;
        }

        return authManager.refreshToken(token);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, AuthManager> authManagerMap = context.getBeansOfType(AuthManager.class);
        for (AuthManager item : authManagerMap.values()) {
            if (authManagers == null) {
                authManagers = new HashMap<>(authManagerMap.size());
            }
            authManagers.put(item.getAuthenticationType(), item);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
