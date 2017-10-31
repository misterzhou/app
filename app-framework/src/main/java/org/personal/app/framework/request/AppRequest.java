package org.personal.app.framework.request;

import org.personal.app.commons.utils.IPUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created at: 2017-10-29 12:49
 *
 * @author guojing
 */
public class AppRequest {

    public static final String REQUESTID_HEADER = "X-requestID";
    public static final String REMOTEIP_HEADER = "X-RemoteIP";
    public static final String X_APP_UID_HEADER = "X-App-UID";
    public static final String X_APP_UDID_HEADER = "X-App-UDID";

    private HttpServletRequest request;
    private Map<String, Object> parameters = new HashMap<>();

    public AppRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getUri() {
        return request.getRequestURI();
    }

    public String getHeader(String name) {
        return request.getHeader(name);
    }

    public Object getParameter(String name) {
        return request.getParameter(name);
    }

    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
    }

    public String getRequestURI() {
        return request.getRequestURI();
    }

    public String getRemoteIp() {
        String remoteIp = request.getHeader(REMOTEIP_HEADER);
        return remoteIp != null ? remoteIp : IPUtils.getRealIpAddr(request);
    }

    public String getUdid() {
        return request.getHeader(X_APP_UDID_HEADER);
    }
}