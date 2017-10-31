package org.personal.app.framework.context;

import org.personal.app.commons.AppException;
import org.personal.app.commons.utils.AppStringUtils;
import org.personal.app.framework.apiprops.AuthType;
import org.personal.app.framework.apiprops.BaseInfo;
import org.personal.app.framework.apiprops.RateLimit;
import org.personal.app.framework.auth.AuthResource;
import org.personal.app.framework.auth.AuthenticationProvider;
import org.personal.app.framework.request.AppRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created at: 2017-10-28 22:49
 *
 * @author guojing
 */
//@Component
public class DefaultRequestMappingHandlerAdapter extends RequestMappingHandlerAdapter {

    private static final String[] STATIC_RESOURCES = new String[]{"jsp", "html", "css", "js", "ico", "png", "jpg", "gif"};

    @Resource
    AuthenticationProvider authenticationProvider;

    @Override
    protected ModelAndView handleInternal(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) throws Exception {
        if (AppStringUtils.equals(request.getRequestURI(), "/error")
                || AppStringUtils.startsWith(request.getRequestURI(), "/swagger-resources")
                || AppStringUtils.endsWithAny(request.getRequestURI(), STATIC_RESOURCES)) {
            return super.handleInternal(request, response, handlerMethod);
        }

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
        rc.setCurrentUid(authResource.getUid());
        rc.setIp(appRequest.getRemoteIp());
        rc.setUdid(appRequest.getUdid() != null ? appRequest.getUdid() : authResource.getUdid());

        RateLimit[] rateLimits = null;
        if (baseInfo != null && baseInfo.rateLimits().length == 0) {
            rateLimits = baseInfo.rateLimits();

        }

        return super.handleInternal(request, response, handlerMethod);
    }

}
