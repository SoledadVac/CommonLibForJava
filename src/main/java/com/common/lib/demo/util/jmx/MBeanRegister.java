package com.common.lib.demo.util.jmx;

import com.common.lib.demo.javasource.spi.InstalledServices;

import javax.management.ObjectName;
import java.lang.management.PlatformManagedObject;


/**
 * MBean注册器
 */
public interface MBeanRegister {

    static MBeanRegister installed() {
        return Loader.installed;
    }

    void safeRegisterMBean(PlatformManagedObject pmo);

    void safeUnregisterMBean(ObjectName objectName);

    abstract class Loader {
        private static final MBeanRegister installed;

        static {
            MBeanRegister register = InstalledServices.installedFirst(MBeanRegister.class);
            if (register == null) {
                register = new MBeanRegister() {
                    @Override
                    public void safeRegisterMBean(PlatformManagedObject pmo) {
                    }

                    @Override
                    public void safeUnregisterMBean(ObjectName objectName) {
                    }
                };
            }
            installed = register;
        }
    }
}
