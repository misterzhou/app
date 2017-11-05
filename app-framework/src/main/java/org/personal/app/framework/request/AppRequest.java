package org.personal.app.framework.request;

import org.personal.app.commons.utils.IPUtils;
import org.personal.app.framework.auth.AuthenticationType;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created at: 2017-10-29 12:49
 *
 * @author guojing
 */
public class AppRequest {

    public static final String PARAM_TOKEN = HttpHeaders.AUTHORIZATION.toLowerCase();
    public static final String REQUESTID_HEADER = "X-requestID";
    public static final String REMOTEIP_HEADER = "X-RemoteIP";
    public static final String X_APP_UID_HEADER = "X-App-UID";
    public static final String X_APP_UDID_HEADER = "X-App-UDID";

    private HttpServletRequest originalRequest;
    private Map<String, Object> parameters = new LinkedHashMap<>();

    public AppRequest(HttpServletRequest originalRequest) {
        this.originalRequest = originalRequest;

    }

    public HttpServletRequest getOriginalRequest() {
        return originalRequest;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public AppRequest setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public void addParameter(String key, Object value) {
        if (parameters == null) {
            parameters = new LinkedHashMap<>();
        }
        parameters.put(key, value);
    }

    public void addParameters(Object params) {
        if (parameters == null) {
            parameters = new LinkedHashMap<>();
        }
    }

    public String getHeader(String name) {
        return originalRequest.getHeader(name);
    }

    public String getParameter(String name) {
        return originalRequest.getParameter(name);
    }

    public Map<String, String[]> getParameterMap() {
        return originalRequest.getParameterMap();
    }

    public String getRequestAPI() {
        return originalRequest.getRequestURI();
    }

    public String getRequestURI() {
        return originalRequest.getRequestURI();
    }

    public String getRemoteIp() {
        String remoteIp = originalRequest.getHeader(REMOTEIP_HEADER);
        return remoteIp != null ? remoteIp : IPUtils.getRealIpAddr(originalRequest);
    }

    public String getUdid() {
        return originalRequest.getHeader(X_APP_UDID_HEADER);
    }

    public AuthenticationType getAuthenticationType() {
        String authorization = originalRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null) {
            authorization = originalRequest.getParameter(PARAM_TOKEN);
        }

        AuthenticationType result = AuthenticationType.APP_AUTH;
        if (authorization != null) {
            result = AuthenticationType.parseTypeFromAuthHeader(authorization);
        }
        return result;
    }

}
