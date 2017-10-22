package org.personal.app.framework.context;

import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created at: 2017-10-22 12:37
 *
 * @author guojing
 */
public class DefaultRequestIDGenerator implements RequestIDGenerator {

    private final AtomicLong requestID = new AtomicLong(0);
    private String hostname;

    static final DefaultRequestIDGenerator instance = new DefaultRequestIDGenerator();

    private DefaultRequestIDGenerator() {
        try {
            this.hostname = java.net.InetAddress.getLocalHost().getHostName();
            int idx = this.hostname.indexOf('.');
            if (idx > 0) {
                this.hostname = this.hostname.substring(0, idx);
            }
        } catch (UnknownHostException e) {
            this.hostname = "localhost";
        }
    }

    public static DefaultRequestIDGenerator getInstance() {
        return instance;
    }

    @Override
    public String nextID() {
        return this.hostname + "-" + requestID.getAndIncrement();
    }
}
