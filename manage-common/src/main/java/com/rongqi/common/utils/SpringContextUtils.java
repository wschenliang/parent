package com.rongqi.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext _applicationContext = null;

    public SpringContextUtils() {
    }

    public void _setApplicationContext(ApplicationContext context) {
        _applicationContext = context;
    }

    public static ApplicationContext getApplicationContext() {
        return _applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return _applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        T t = null;
        if (_applicationContext.containsBean(beanName)) {
            t = _applicationContext.getBean(beanName, clazz);
        }

        return t;
    }

    public void destroy() throws Exception {
        this._setApplicationContext((ApplicationContext)null);
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this._setApplicationContext(context);
    }
}
