package org.personal.app.framework.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.personal.app.commons.AppException;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created at: 2017-11-01 08:03
 *
 * @author guojing
 */
@Component
@Aspect
@Order(1)
public class RequestParamValidatorAspect extends BasePointCut {

    ParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator validator = factory.getValidator().forExecutables();

    @Before("controller()")
    public void validateRequestParam(JoinPoint jp) throws Throwable {
        Object target = jp.getThis();
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        Object[] args = jp.getArgs();
        Set<ConstraintViolation<Object>> validResult = validator.validateParameters(target, method, args);
        if (validResult.isEmpty()) {
            return;
        }

        final boolean hasRequestBody = hasRequestBody(method);
        String [] methodParamNames = paramNameDiscoverer.getParameterNames(method);
        List<String> invalidParams = validResult.stream().map(constraintViolation -> {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            String paramName = null;
            if (hasRequestBody) {
                paramName = pathImpl.getLeafNode().getName();
            } else {
                int paramIndex = pathImpl.getLeafNode().getParameterIndex();
                paramName = methodParamNames[paramIndex];
            }
            return String.format("%s %s", paramName, constraintViolation.getMessage());
        }).collect(Collectors.toList());
        throw AppException.newParamException(invalidParams.toString());
    }

    private boolean hasRequestBody(Method method) {
        boolean hasRequestBody = false;
        for (Parameter param : method.getParameters()) {
            if (param.isAnnotationPresent(RequestBody.class)) {
                hasRequestBody = true;
                break;
            }
        }
        return hasRequestBody;
    }

}
