package com.common.lib.demo.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.support.ApplicationObjectSupport;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/8/6
 * \* Time: 上午11:58
 * \* Description:
 * \
 */
public class SpringContainerSupport  extends ApplicationObjectSupport implements BeanClassLoaderAware, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean{

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void setBeanName(String s) {

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
