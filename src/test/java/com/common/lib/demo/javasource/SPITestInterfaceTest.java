package com.common.lib.demo.javasource;

import org.junit.Test;

import java.util.ServiceLoader;

import static org.junit.Assert.*;

public class SPITestInterfaceTest {


    @Test
    public void consoleSth() {
        ServiceLoader<SPITestInterface> testInterfaces = ServiceLoader.load(SPITestInterface.class);
        for (SPITestInterface testInterface : testInterfaces) {
            System.out.println(testInterface.consoleSth("aaaaaBBBB"));;
        }
    }
}