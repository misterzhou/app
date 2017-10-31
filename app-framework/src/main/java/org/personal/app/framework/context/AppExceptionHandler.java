package org.personal.app.framework.context;

import org.personal.app.commons.AppException;
import org.personal.app.framework.packages.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created at: 2017-10-22 21:13
 *
 * @author guojing
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
        ApiResult failedResult = new ApiResult(ex);
        return new ResponseEntity<>(failedResult, null, HttpStatus.OK);
    }
}
