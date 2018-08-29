package com.common.lib.demo.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import java.util.Objects;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/8/6
 * \* Time: 下午12:14
 * \* Description:
 * \
 */
 abstract class SpringContainerUtils {
    public SpringContainerUtils() {
    }

    public static boolean hasSingletonBean(Object beanFactory, Class<?> beanClass) {
        if (!(beanFactory instanceof BeanFactory)) {
            throw new IllegalArgumentException("BeanFactory is required");
        } else {
            Objects.requireNonNull(beanClass, "BeanClass must not be null");
            BeanFactory factory = (BeanFactory)beanFactory;

            try {
                return factory.getBean(beanClass) != null;
            } catch (Exception var4) {
                return false;
            }
        }
    }

    public static <T> T getBean(ApplicationContext applicationContext, Class<T> beanClass) {
        try {
            return applicationContext.getBean(beanClass);
        } catch (Exception var3) {
            return null;
        }
    }

    public static <T> T getBean(ApplicationContext applicationContext, String beanName, Class<T> beanClass) {
        try {
            return applicationContext.getBean(beanName, beanClass);
        } catch (Exception var4) {
            return null;
        }
    }
}
