package com.common.lib.demo.spring;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.convert.ConversionService;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/8/6
 * \* Time: 上午11:58
 * \* Description:
 * \
 */
public class SpringContainerSupport  extends ApplicationObjectSupport implements BeanClassLoaderAware, BeanNameAware, BeanFactoryAware, InitializingBean, DisposableBean{

    protected String beanName;
    protected ClassLoader beanClassLoader;
    protected BeanFactory beanFactory;
    protected ApplicationContext applicationContext;

    public SpringContainerSupport() {

    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected void initApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
        super.initApplicationContext(context);
    }

    public void afterPropertiesSet() throws Exception {

    }

    public void destroy() throws Exception {
    }

    /** @deprecated */
    @Deprecated
    protected <T> T getBean(Class<T> beanClass) {
        return SpringContainerUtils.getBean(this.applicationContext, beanClass);
    }

    /** @deprecated */
    @Deprecated
    protected <T> T getBean(String beanName, Class<T> beanClass) {
        return SpringContainerUtils.getBean(this.applicationContext, beanName, beanClass);
    }

    protected void afterPropertiesSetCallback() throws Exception {
    }
}
