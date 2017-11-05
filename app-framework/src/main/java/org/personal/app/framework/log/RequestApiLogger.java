package org.personal.app.framework.log;

import org.personal.app.commons.logger.RequestLogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created at: 2017-11-02 22:52
 *
 * @author guojing
 */
public class RequestApiLogger {

    private static Logger apiLogger = LoggerFactory.getLogger("api");

    private static ThreadLocal<RequestLogRecord> logRecord = new ThreadLocal<RequestLogRecord>(){
        @Override
        protected RequestLogRecord initialValue() {
            return RequestLogRecord.newLogRecord();
        }
    };

    public static RequestLogRecord getCurrLogRecord() {
        return logRecord.get();
    }

    public static void clearCurrLogRecord() {
        logRecord.remove();
    }

    public static void doLog() {
        RequestLogRecord logRecord = getCurrLogRecord();
        apiLogger.info(logRecord.toString());
    }

}
