package org.personal.app.framework.apiprops;

/**
 * Created at: 2017-10-22 11:29
 *
 * @author guojing
 */
public enum ApiStatus {

    DEV(1, "开发中"),
    BETA(2, "Beta内测"),
    INTERNAL(3, "已上线的内部接口"),
    PUBLIC(4, "已上线的公开接口");

    private int id;

    private String desc;

    ApiStatus(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

}
