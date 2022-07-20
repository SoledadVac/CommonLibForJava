package com.common.lib.demo.javasource.spiutil;

import com.common.lib.demo.javasource.spi.SPITestInterface;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class SPITestInterfaceTest {


    @Test
    public void consoleSth() {
        ServiceLoader<SPITestInterface> testInterfaces = ServiceLoader.load(SPITestInterface.class);
        for (SPITestInterface testInterface : testInterfaces) {
            System.out.println(testInterface.consoleSth("aaaaaBBBB"));
        }
    }

    @Test
    public void useSpiServiceSth() {

    }


    @Test
    public void dynamicSPI() throws Exception {
        List<String> filePathList = new ArrayList<>();
        filePathList.add("/Users/liuhuichao/repository/mysql/mysql-connector-java/8.0.25/mysql-connector-java-8.0.25.jar");
        List<URL> urlList = new ArrayList<>();
        for (String path : filePathList) {
            File dockerJarFile = new File(path);
            urlList.add(dockerJarFile.toURI().toURL());
        }
        ClassLoader classLoader = new URLClassLoader(urlList.toArray(new URL[]{}), Thread.currentThread().getContextClassLoader());
        ServiceLoader<DataSource> dataSources = ServiceLoader.load(DataSource.class, classLoader);
        for (DataSource dataSource : dataSources) {
            Assert.assertNotNull(dataSource);
        }
    }

}