package org.personal.app.framework.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.personal.app.commons.AppException;
import org.personal.app.commons.logger.AppLogger;
import org.personal.app.commons.logger.RequestLogRecord;
import org.personal.app.framework.context.RequestContext;
import org.personal.app.framework.context.ThreadLocalContext;
import org.personal.app.framework.packages.ApiResult;
import org.personal.app.framework.request.AppRequest;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created at: 2017-11-02 22:17
 *
 * @author guojing
 */
@Component
@Aspect
@Order(1)
public class LogPrinterAspect extends BasePointCut {

    ParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Around("controller()")
    public Object printApiLog(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestLogRecord logRecord = initLogRecord();
        RequestContext rc = ThreadLocalContext.getRequestContext();
        AppRequest request = rc.getAppRequest();

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String [] methodParamNames = paramNameDiscoverer.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter param = parameters[i];
            if (param.getAnnotations().length == 0) {
                continue;
            }

            if (param.isAnnotationPresent(PathVariable.class) || param.isAnnotationPresent(RequestParam.class)) {
                request.addParameter(methodParamNames[i], args[i]);
            } else if (param.isAnnotationPresent(RequestBody.class)) {
                JSONObject paramBody = (JSONObject) JSONObject.toJSON(args[i]);
                request.getParameters().putAll(paramBody);
            }
        }
        Object originalResult = null;
        ApiResult apiResult = null;
        try {
            originalResult = joinPoint.proceed();
            apiResult = new ApiResult(originalResult);
            logRecord.setParameters(request.getParameters()).setRespCode(apiResult.getCode()).setResponse(apiResult.toJSONString());
            AppLogger.API_LOGGER.info(logRecord.toString());
        } catch (Exception e) {
            if (e instanceof AppException) {
                apiResult = new ApiResult(((AppException) e));
                logRecord.setParameters(request.getParameters()).setRespCode(apiResult.getCode()).setResponse(apiResult.toJSONString());
                AppLogger.API_LOGGER.info(logRecord.toString());
            } else {
                AppLogger.error("接口请求失败，api: {}, param: {}", logRecord.getApi(), logRecord.getParameters(), e);
            }
            throw e;
        }
        return originalResult;
    }

    private RequestLogRecord initLogRecord() {
        RequestContext rc = ThreadLocalContext.getRequestContext();
        AppRequest request = rc.getAppRequest();
        return RequestLogRecord.newLogRecord().setRequestID(rc.getRequestID())
                .setUid(rc.getCurrentUid())
                .setApi(request.getRequestAPI());
    }

}
