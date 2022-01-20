package com.common.lib.demo.javasource;

/**
 * \* Created: liuhuichao
 * \* Date: 2022/1/20
 * \* Time: 上午11:44
 * \* Description: a impl for SPI test
 * \
 */
public class DefaultSPITestInterfaceImplTwo implements SPITestInterface {
    @Override
    public String consoleSth(String source) {
        if (source == null || source.length() < 1) {
            return "";
        }
        return source.toUpperCase();
    }
}
