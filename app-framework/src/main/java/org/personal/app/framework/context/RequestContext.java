package org.personal.app.framework.context;

import com.alibaba.fastjson.annotation.JSONField;
import org.personal.app.framework.request.AppRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created at: 2017-10-22 12:15
 *
 * @author guojing
 */
public class RequestContext {

    /**
     * 本次请求id
     */
    private String requestID;
    /**
     * 用户id
     */
    private String currentUid;
    /**
     * 设备标识
     */
    private String udid;
    private String ip;
    /**
     * 平台
     */
    private String platform;
    private Map<String, Object> attributes;

    @JSONField(serialize = false)
    private transient AppRequest appRequest;

    public RequestContext() {}

    public RequestContext(String requestID) {
        this.requestID = requestID;
        this.attributes = new HashMap<>();
    }

    public RequestContext(String requestID, String currentUid) {
        this(requestID);
        this.currentUid = currentUid;
    }

    public RequestContext(String requestID, String currentUid, String udid) {
        this(requestID, currentUid);
        this.udid = udid;
    }

    public String getCurrentUid() {
        return currentUid;
    }

    public RequestContext setCurrentUid(String currentUid) {
        this.currentUid = currentUid;
        return this;
    }

    public String getUdid() {
        return udid;
    }

    public RequestContext setUdid(String udid) {
        this.udid = udid;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public RequestContext setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getRequestID() {
        return requestID;
    }

    public RequestContext setRequestID(String requestID) {
        this.requestID = requestID;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public RequestContext setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public RequestContext setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public AppRequest getAppRequest() {
        return appRequest;
    }

    public RequestContext setAppRequest(AppRequest appRequest) {
        this.appRequest = appRequest;
        return this;
    }
}
