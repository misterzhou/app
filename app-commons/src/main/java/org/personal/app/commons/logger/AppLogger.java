package org.personal.app.commons.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created at: 2017-10-26 23:30
 *
 * @author guojing
 */
public class AppLogger {

    public static Logger debugLog = LoggerFactory.getLogger("debug");
    public static Logger infoLog = LoggerFactory.getLogger("info");
    public static Logger warnLog = LoggerFactory.getLogger("warn");
    public static Logger errorLog = LoggerFactory.getLogger("error");
    public static final Logger API_LOGGER = LoggerFactory.getLogger("api");


    public static boolean isTraceEnabled() {
        return debugLog.isTraceEnabled();
    }

    public static boolean isDebugEnabled() {
        return debugLog.isDebugEnabled();
    }

    public static void trace(Object msg) {
        debugLog.trace(formatMsg(msg));
    }

    public static void trace(String tag, Object msg) {
        debugLog.trace(formatMsg(tag, msg));
    }

    public static void debug(Object msg) {
        if (debugLog.isDebugEnabled()) {
            debugLog.debug(formatMsg(msg));
        }
    }

    public static void debug(String tag, Object msg) {
        if (msg == null) {
            return;
        }
        if (debugLog.isDebugEnabled()) {
            debugLog.debug(formatMsg(tag, msg));
        }

    }

    public static void info(Object msg) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(msg));
        }
    }

    public static void info(Object msg, long response_time) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(msg));
        }
    }

    public static void info(String tag, Object msg) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(tag, msg));
        }
    }

    public static void warn(String tag, Object msg) {
        warnLog.warn(formatMsg(tag, msg));
    }

    public static void warn(String tag, Object msg, Throwable e) {
        warnLog.warn(formatMsg(tag, msg));
    }

    public static void warn(Object msg) {
        warnLog.warn(formatMsg(msg));
    }

    public static void error(String tag, Object msg, Throwable e) {
        errorLog.error(formatMsg(tag, msg), e);
    }

    public static void error(String tag, String msg) {
        errorLog.error(formatMsg(tag, msg));
    }

    public static void error(Object msg) {
        if (msg instanceof Throwable) {
            errorLog.error(formatMsg(((Throwable) msg).getMessage()), (Throwable) msg);
        } else {
            errorLog.error(formatMsg(msg));
        }

    }

    public static void error(String format, Object... arguments) {
        errorLog.error(format, arguments);
    }

    public static void error(Object msg, Throwable e) {
        errorLog.error(formatMsg(msg), e);
    }

    private static String formatMsg(Object msg) {
        return String.format("%s", msg == null ? "null" : msg.toString());
    }

    private static String formatMsg(String tag, Object msg) {
        return String.format("%s\t%s", tag, msg == null ? "null" : msg.toString());
    }

    private static String getStackTraceText(Throwable t) {
        if (t == null) return "";
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            return sw.toString();
        } catch (Exception e) {
            AppLogger.nativeError(e);
        }
        return "";
    }

    public static void nativeError(Object msg) {
        if (msg instanceof Throwable) {
            errorLog.error(formatMsg(((Throwable) msg).getMessage()), (Throwable) msg);
        } else {
            errorLog.error(formatMsg(msg));
        }
    }

    public static void nativeWarn(Object msg) {
        warnLog.warn(formatMsg(msg));
    }

    public static void nativeInfo(Object msg) {
        if (msg == null) {
            return;
        }
        if (infoLog.isInfoEnabled()) {
            infoLog.info(formatMsg(msg));
        }
    }

}
