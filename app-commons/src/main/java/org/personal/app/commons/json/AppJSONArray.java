package org.personal.app.commons.json;

import com.alibaba.fastjson.JSONArray;

/**
 * Created at: 2017-10-27 00:00
 *
 * @author guojing
 */
public class AppJSONArray extends JSONArray {

    public AppJSONArray() {}

    public static AppJSONArray newArray() {
        return new AppJSONArray();
    }

}
