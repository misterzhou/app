package org.personal.app.commons.utils;

import java.nio.charset.Charset;

/**
 * Created at: 2017-10-27 09:16
 *
 * @author guojing
 */
public class UMSCharsets {

    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    /**
     * ISO-8859-1: ISO Latin Alphabet Number 1 (ISO-LATIN-1).
     */
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    /**
     * UTF-8: eight-bit UCS Transformation Format.
     */
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * UTF-16BE: sixteen-bit UCS Transformation Format, big-endian byte order.
     */
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    /**
     * UTF-16LE: sixteen-bit UCS Transformation Format, little-endian byte order.
     */
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    /**
     * UTF-16: sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order
     * mark.
     */
    public static final Charset UTF_16 = Charset.forName("UTF-16");

    private UMSCharsets() {
    }

}
