package org.personal.app.framework.context;

/**
 * Created at: 2017-10-22 12:29
 *
 * @author guojing
 */
public class ThreadLocalContext extends InheritableThreadLocal<RequestContext> {

    private static RequestIDGenerator requestIDGenerator = DefaultRequestIDGenerator.getInstance();
    private static ThreadLocalContext instance = new ThreadLocalContext();

    @Override
    public RequestContext initialValue() {
        return new RequestContext(requestIDGenerator.nextID());
    }

    public static RequestContext getRequestContext() {
        return instance.get();
    }

    public static void clear() {
        instance.remove();
    }

}
