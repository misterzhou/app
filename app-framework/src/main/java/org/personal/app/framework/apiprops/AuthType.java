package org.personal.app.framework.apiprops;

/**
 * Created at: 2017-10-22 11:32
 *
 * 接口认证类型
 *
 * @author guojing
 */
public enum AuthType {

    /**
     * 可选，有则验证，无则不验证
     */
    OPTIONAL(),
    /**
     * 外部需要认证
     */
    OUTER(),
    /**
     * 必须验证
     */
    REQUIRED();

    AuthType(){}

}
