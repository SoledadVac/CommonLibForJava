package com.common.lib.demo.javasource.spi;

import java.util.Objects;

/**
 * \* Created: liuhuichao
 * \* Date: 2022/1/20
 * \* Time: 上午11:42
 * \* Description: this is an interface for test SPI
 * \
 */
public interface SPITestInterface {

    static SPITestInterface getInstance() {
        return Objects.requireNonNull(Holder.installed, "No AlpsThreadPool installed, alps-core is required");
    }

    String consoleSth(String source);

    abstract class Holder {
        private static final SPITestInterface installed;

        static {
            installed = InstalledServices.installedFirst(SPITestInterface.class);
        }
    }

}
