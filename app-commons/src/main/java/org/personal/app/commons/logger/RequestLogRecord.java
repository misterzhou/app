package org.personal.app.commons.logger;

import org.apache.commons.collections.MapUtils;
import org.personal.app.commons.json.AppJSONObject;
import org.personal.app.commons.utils.AppDateFormatUtils;
import org.personal.app.commons.utils.AppStringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created at: 2017-11-02 23:08
 *
 * @author guojing
 */
public class RequestLogRecord {

    private static final String SPLIT = "##";

    private Date date = new Date();
    /**
     * 请求id
     */
    private String requestID;
    /**
     * 请求的用户id
     */
    private String uid;
    /**
     * 接口响应时间
     */
    private int respTime;
    /**
     * 接口
     */
    private String api;
    /**
     * string类型请求参数
     */
    private String parameterStr;
    /**
     * 接口请求参数
     */
    private Map<String, Object> parameters = Collections.emptyMap();
    /**
     * 响应码
     *
     * @see org.personal.app.commons.ErrorCode
     */
    private int respCode;
    /**
     * 响应结果
     */
    private String response;

    public RequestLogRecord() {
    }

    public static RequestLogRecord newLogRecord() {
        return new RequestLogRecord().setDate(new Date());
    }

    public Date getDate() {
        return date;
    }

    public RequestLogRecord setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getApi() {
        return api;
    }

    public RequestLogRecord setApi(String api) {
        this.api = api;
        return this;
    }

    public int getRespTime() {
        return respTime;
    }

    public RequestLogRecord setRespTime(int respTime) {
        this.respTime = respTime;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public RequestLogRecord setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getRequestID() {
        return requestID;
    }

    public RequestLogRecord setRequestID(String requestID) {
        this.requestID = requestID;
        return this;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public RequestLogRecord setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }

    public int getRespCode() {
        return respCode;
    }

    public RequestLogRecord setRespCode(int respCode) {
        this.respCode = respCode;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public RequestLogRecord setResponse(String response) {
        this.response = response;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(AppDateFormatUtils.format(date, AppDateFormatUtils.TIMESTAMP_PATTERN));
        buf.append(SPLIT);
        buf.append(requestID);
        buf.append(SPLIT);
        buf.append(AppStringUtils.isNotBlank(uid) ? uid : "unknown");
        buf.append(SPLIT);
        if (respTime <= 0) {
            respTime = (int) (System.currentTimeMillis() - this.date.getTime());
        }
        buf.append(respTime);
        buf.append(SPLIT);
        buf.append(api);
        buf.append(SPLIT);
        buf.append(getParameterStr());
        buf.append(SPLIT);
        buf.append(respCode);
        buf.append(SPLIT);
        buf.append(response);
        return buf.toString();
    }

    private String getParameterStr() {
        if (parameterStr != null) {
            return parameterStr;
        }
        if (MapUtils.isEmpty(parameters)) {
            return "{}";
        }

        AppJSONObject paramJson = AppJSONObject.newObject(true);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            paramJson.put(entry.getKey(), entry.getValue());
        }
        return paramJson.toJSONString();
    }

    private String escapePassword(String key, String value) {
        if (AppStringUtils.equals("password", key) || AppStringUtils.equals("old_password", key) || AppStringUtils.equals("new_password", key)) {
            return "***";
        }
        return value;
    }
}
