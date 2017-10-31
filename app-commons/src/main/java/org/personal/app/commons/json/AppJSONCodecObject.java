package org.personal.app.commons.json;

/**
 * Created at: 2017-10-27 00:09
 *
 * @author guojing
 */
public interface AppJSONCodecObject extends AppJSONObjectAware, AppJSONStringAware {

    void initWithJSON(AppJSONObject jsonObject);

}
