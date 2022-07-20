package com.common.lib.demo.util.jmx;

public class JMXInvocationException extends RuntimeException {
    private static final long serialVersionUID = 3438031730883365463L;

    public JMXInvocationException() {
    }

    public JMXInvocationException(String message) {
        super(message);
    }

    public JMXInvocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JMXInvocationException(Throwable cause) {
        super(cause);
    }
}
