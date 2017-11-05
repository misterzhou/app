package org.personal.app.commons.utils;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

/**
 * Created at: 2017-11-02 23:31
 *
 * @author guojing
 */
public class AppDateFormatUtils extends DateFormatUtils {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.S";

    public static final String DEFAULT_DATE_PATTERN = DATE_TIME_PATTERN;

    public static String format(Date date) {
        return format(date, DEFAULT_DATE_PATTERN);
    }

}
