package com.common.lib.demo.jdk.classload;

import org.apache.tools.ant.util.ReflectUtil;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadClassTest {

    private static final String LOAD_BASE_PATH = "/Users/liuhuichao/repository/com/baomidou/mybatis-plus-core/3.0.1/mybatis-plus-core-3.0.1.jar";

    @Test
    public void testLoadByPath() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        String jarFilePath = LOAD_BASE_PATH;
//        File jarFile = new File(jarFilePath);
//        if (!jarFile.exists()) {
//            throw new IllegalStateException("can not find jarFile: " + jarFilePath);
//        }
//        UDTFClassloader udtfClassloader = new UDTFClassloader(
//                new URL[]{jarFile.toURI().toURL()});
        //com.baomidou.mybatisplus.core.toolkit.ArrayUtils
        //public static boolean isEmpty(Object[] array) {
        //        return array == null || array.length == 0;
        //    }
        //Class utilClazz = udtfClassloader.loadClass("com.baomidou.mybatisplus.core.toolkit.ArrayUtils");
        Class utilClazz = ClazzGetter.getClazz("com.baomidou.mybatisplus.core.toolkit.ArrayUtils", "1.0", "mybatis-plus-core-3.0.1.jar");
        Object[] array = new Object[1];
        Method targetMethod = ReflectionUtils.findMethod(utilClazz, "isEmpty",  null);
        Object result = ReflectionUtils.invokeMethod(targetMethod, null, array);
        boolean r1 = (boolean) result;
        System.out.println(r1);
    }
}
