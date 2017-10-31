package org.personal.app.commons.json;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created at: 2017-10-26 23:57
 *
 * @author guojing
 */
public class AppJSONObject extends JSONObject {
    
    public AppJSONObject() {}

    public AppJSONObject(Map map) {
        super(map);
    }

    public static AppJSONObject newObject() {
        return new AppJSONObject();
    }

    public AppJSONObject getJSONObject(String key) {
        return (AppJSONObject) super.getJSONObject(key);
    }

    public byte getByteValue(String key, byte defaultValue) {
        byte value = super.getByteValue(key);
        return value != (byte) 0 ? value : defaultValue;
    }

    public long getLongValue(String key, long defaultValue) {
        long value = super.getLongValue(key);
        return value != 0L ? value : defaultValue;
    }

    public AppJSONObject append(String key, String value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, Number value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, AppJSONObject value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, List value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, AppJSONArray value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, AppJSONStringAware value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, AppJSONObjectAware value) {
        this.put(key, value);
        return this;
    }

    public AppJSONObject append(String key, Boolean value) {
        this.put(key, value);
        return this;
    }
    
}
