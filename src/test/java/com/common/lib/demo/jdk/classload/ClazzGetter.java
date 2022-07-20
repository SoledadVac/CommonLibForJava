package com.common.lib.demo.jdk.classload;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ClazzGetter{

    private static final String LOAD_BASE_PATH = "/Users/liuhuichao/repository/com/baomidou/mybatis-plus-core/3.0.1";
    private static ConcurrentReferenceHashMap<String, Class> typeClazzMap = new ConcurrentReferenceHashMap<>(2 << 5);

    public static Class getClazz(String className, String version, String jarFileName) throws MalformedURLException, ClassNotFoundException {
        String key = className + "_" + version;
        if (typeClazzMap.containsKey(key)) {
            return typeClazzMap.get(key);
        }
        String jarFilePath = LOAD_BASE_PATH + "/" + jarFileName;
        File jarFile = new File(jarFilePath);
        if (!jarFile.exists()) {
            throw new IllegalStateException("can not find jarFile: " + jarFilePath);
        }
        UDTFClassloader udtfClassloader = new UDTFClassloader(
                new URL[]{jarFile.toURI().toURL()});
        Class utilClazz = udtfClassloader.loadClass(className);
        typeClazzMap.put(key, utilClazz);
        return utilClazz;
    }

}
