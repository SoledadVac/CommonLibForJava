package com.common.lib.demo.util.jmx;

import javax.management.ObjectName;
import java.util.concurrent.ConcurrentHashMap;

public class MBeanInstancePool extends ConcurrentHashMap<ObjectName, Object> {

    private MBeanInstancePool() {
    }

    public static final MBeanInstancePool INSTANCE = new MBeanInstancePool();

    public void close() {
        keySet().forEach(MBeanServerUtils::safeUnregisterMBean);
        clear();
    }
}
