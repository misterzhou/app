package org.personal.app.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Created at: 2017-10-27 08:35
 *
 * @author guojing
 */
public class Base62Utils {

    private static final char[] chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final int encodeBlockSize = 7;
    private static final int decodeBlockSize = 4;
    private static final int intgers[] = new int[128];

    static {
        for (int i = 0; i < chars.length; i++) {
            intgers[chars[i]] = i;
        }
    }

    /**
     * 将数字转换成62进制字符串
     *
     * @return string
     */
    public static String encode(long val) {
        String str = "";
        String strVal = String.valueOf(val);
        int midlen = strVal.length();
        int segments = midlen / encodeBlockSize;
        int start = midlen;
        for (int i = 0; i < segments; i++) {
            start -= encodeBlockSize;
            String seq = strVal.substring(start, start + encodeBlockSize);
            seq = encodeSegment(seq);
            str = leftPad(seq, '0', decodeBlockSize) + str;
        }

        if (start > 0) {
            str = encodeSegment(strVal.substring(0, start)) + str;
        }
        return str;
    }

    public static String generateBase62Key(long appKey) {
        String idStr = Long.toString(appKey);
        int idlen = idStr.length();
        int len = (int) Math.ceil((double) idlen / encodeBlockSize);
        int start = idlen;
        String str = "";
        for (int i = 1; i < len; i++) {
            start -= encodeBlockSize;
            String seg = idStr.substring(start, start + encodeBlockSize);
            seg = encodeSegment(seg);
            str = StringUtils.leftPad(seg, decodeBlockSize, '0') + str;
        }
        str = encodeSegment(idStr.substring(0, 0 + start)) + str;
        return str;
    }

    /**
     * 将62进制字符串转成10进制数字
     *
     * @return long
     */
    public static long decode(String str) {
        String num = "";
        int strlen = str.length();
        int segments = strlen / decodeBlockSize;
        int start = strlen;
        for (int i = 0; i < segments; i++) {
            start -= decodeBlockSize;
            String seg = str.substring(start, start + decodeBlockSize);
            seg = String.valueOf(decodeSegment(seg));
            num = leftPad(seg, '0', encodeBlockSize) + num;
        }
        if (start > 0) {
            num = decodeSegment(str.substring(0, start)) + num;
        }
        return NumberUtils.toLong(num);
    }

    private static String leftPad(String src, char padchar, int len) {
        if (src == null || src.length() >= len) {
            return src;
        }

        int padlen = len - src.length();
        String retr = src;
        for (int i = 0; i < padlen; i++) {
            retr = padchar + retr;
        }
        return retr;
    }

    /**
     * 将10进制转换成62进制
     *
     * @return string
     */
    private static String encodeSegment(String numStr) {
        long val = Long.parseLong(numStr);
        String result = "";
        int i;
        do {
            i = (int) (val % 62);
            result = chars[i] + result;
            val = (val - i) / 62;
        } while (val > 0);
        return result;
    }

    /**
     * 将62进制转换成10进制
     *
     * @return string
     */
    private static long decodeSegment(String val) {
        long retr = 0;
        int len = val.length();
        char[] charArray = val.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            retr = retr + intgers[charArray[i]] * (long) Math.pow(62, len - i - 1);
        }
        return retr;
    }

}
