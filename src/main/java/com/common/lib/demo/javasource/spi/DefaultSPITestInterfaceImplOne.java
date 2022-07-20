package com.common.lib.demo.javasource.spi;

/**
 * \* Created: liuhuichao
 * \* Date: 2022/1/20
 * \* Time: 上午11:43
 * \* Description: a impl for SPI test
 * \
 */
public class DefaultSPITestInterfaceImplOne implements SPITestInterface {
    @Override
    public String consoleSth(String source) {
        return source;
    }
}
