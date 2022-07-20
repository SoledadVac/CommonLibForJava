package com.common.lib.demo.util.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.management.PlatformManagedObject;
import java.util.concurrent.*;

abstract public class MBeanServerUtils {
    private static final Logger logger = LoggerFactory.getLogger(MBeanServerUtils.class);

    /**
     * Safe register MBean instance to server, will not throw exception.
     *
     * @param pmo the platform managed object to be registered, null will be ignore
     */
    public static void safeRegisterMBean(PlatformManagedObject pmo) {
        if (pmo == null) {
            return;
        }
        ObjectName objectName = pmo.getObjectName();
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(pmo, objectName);
            MBeanInstancePool.INSTANCE.put(objectName, pmo);
            logger.trace("MBean registered: {}", objectName);
        } catch (InstanceAlreadyExistsException ex) {
            logger.trace("MBean '{}' already registered, ignore.", objectName);
        } catch (Exception ignore) {
            logger.trace("", ignore);
        }
    }

    /**
     * Safe unregister MBean from server, no exception will be threw.
     *
     * @param objectName the objectName of the MBean to be unregistered
     */
    public static void safeUnregisterMBean(ObjectName objectName) {
        if (objectName == null) {
            return;
        }
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        if (!server.isRegistered(objectName)) {
            return;
        }
        try {
            server.unregisterMBean(objectName);
            MBeanInstancePool.INSTANCE.remove(objectName);
            logger.trace("MBean unregistered: {}", objectName);
        } catch (InstanceNotFoundException e) {
            logger.trace("MBean '{}' already unregistered.", objectName);
        } catch (Exception ignored) {
        }
    }

    public static Object safeGetAttribute(ObjectName objectName, String attribute) {
        if (objectName == null || attribute == null) {
            return null;
        }
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            return server.getAttribute(objectName, attribute);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object safeInvoke(ObjectName objectName, String operationName,
                                    Object[] params, String[] signature) {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        try {
            return server.invoke(objectName, operationName, params, signature);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Object invoke(ObjectName objectName, String operationName,
                                Object[] params, String[] signature) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        return server.invoke(objectName, operationName, params, signature);
    }

    /**
     * Invoke operation on jmx connection with timeout support.
     *
     * @param connection    the jmx connection
     * @param name          instance name
     * @param operationName operation name
     * @param params        operation parameters
     * @param signature     operation signature
     * @param timeout       the maximum time to wait
     * @param unit          the time unit of the timeout argument
     * @return the invocation result
     * @throws TimeoutException          if the wait timed out
     * @throws InstanceNotFoundException if specified instance name not found
     * @throws JMXInvocationException    if invocation error occurs or current thread interrupted while waiting
     */
    public static Object invoke(MBeanServerConnection connection, ObjectName name,
                                String operationName, Object[] params, String[] signature,
                                long timeout, TimeUnit unit)
            throws TimeoutException, InstanceNotFoundException, JMXInvocationException {
        Future<Object> future = Executors.newFixedThreadPool(1).submit(() ->
                connection.invoke(name, operationName, params, signature));
        try {
            return future.get(timeout, unit);
        } catch (InterruptedException ex) {
            throw new JMXInvocationException(ex);
        } catch (ExecutionException ex) {
            if (ex.getCause() instanceof InstanceNotFoundException) {
                throw (InstanceNotFoundException) ex.getCause();
            } else {
                throw new JMXInvocationException(ex.getCause());
            }
        }
    }

    /**
     * Invoke operation which has no parameters on jmx connection with timeout support.
     *
     * @param connection    the jmx connection
     * @param name          instance name
     * @param operationName operation name
     * @param timeout       the maximum time to wait
     * @param unit          the time unit of the timeout argument
     * @return the invocation result
     * @throws TimeoutException          if the wait timed out
     * @throws InstanceNotFoundException if specified instance name not found
     * @throws JMXInvocationException    if invocation error occurs or current thread interrupted while waiting
     */
    public static Object invoke(MBeanServerConnection connection, ObjectName name,
                                String operationName, long timeout, TimeUnit unit)
            throws TimeoutException, InstanceNotFoundException, JMXInvocationException {
        Object[] params = new Object[0];
        String[] signature = new String[0];
        return invoke(connection, name, operationName, params, signature, timeout, unit);
    }

    public static void safeSetAttribute(ObjectName objectName, Attribute attribute) throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.setAttribute(objectName, attribute);
    }

    final public static class DefaultMBeanRegister implements MBeanRegister {
        @Override
        public void safeRegisterMBean(PlatformManagedObject pmo) {
            MBeanServerUtils.safeRegisterMBean(pmo);
        }

        @Override
        public void safeUnregisterMBean(ObjectName objectName) {
            MBeanServerUtils.safeUnregisterMBean(objectName);
        }
    }
}
