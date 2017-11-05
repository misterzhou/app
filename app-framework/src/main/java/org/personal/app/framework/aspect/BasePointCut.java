package org.personal.app.framework.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created at: 2017-11-02 22:27
 *
 * @author guojing
 */
public class BasePointCut {

    /**
     * Controller
     */
    @Pointcut("execution(* org.personal.app..*Controller.*(..))")
    public void controller() {
    }

}
