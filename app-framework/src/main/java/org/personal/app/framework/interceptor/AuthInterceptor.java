package org.personal.app.framework.interceptor;

import org.personal.app.commons.AppException;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.auth.AuthResource;
import org.personal.app.framework.auth.AuthenticationProvider;
import org.personal.app.framework.context.RequestContext;
import org.personal.app.framework.context.ThreadLocalContext;
import org.personal.app.framework.request.AppRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created at: 2017-10-22 22:42
 *
 * @author guojing
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    AuthenticationProvider authenticationProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        BaseInfo baseInfo = method.isAnnotationPresent(BaseInfo.class) ? method.getAnnotation(BaseInfo.class) : null;
        //未加BaseInfo注解默认为: AuthType.client
        AuthType authType = baseInfo != null ? baseInfo.authType() : AuthType.client;
        AuthResource authResource = authenticationProvider.verifyToken(request);
        //不需要认证的接口不检查token
        if (authType.needAuth()) {
            if (authType.support(authResource.getGrantType())) {
                authResource.checkToken();
            } else {
                throw AppException.newInvalidTokenException();
            }
        }

        AppRequest appRequest = new AppRequest(request);
        RequestContext rc = ThreadLocalContext.getRequestContext();
        rc.setAppRequest(appRequest);
        rc.setCurrentUid(authResource.getUid());
        rc.setIp(appRequest.getRemoteIp());
        rc.setUdid(appRequest.getUdid() != null ? appRequest.getUdid() : authResource.getUdid());

        RateLimit[] rateLimits = null;
        if (baseInfo != null && baseInfo.rateLimits().length > 0) {
            rateLimits = baseInfo.rateLimits();

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
