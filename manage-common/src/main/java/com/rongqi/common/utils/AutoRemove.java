package com.rongqi.common.utils;

/**
 * 爬取商品的类
 *
 * @author chenliang
 * @time 2020/1/01
 * @email cleverlove8@outlook.com
 */
public class AutoRemove<T> implements AutoCloseable {

    private ThreadLocal<T> tThreadLocal;

    public AutoRemove(ThreadLocal<T> tThreadLocal) {
        this.tThreadLocal = tThreadLocal;
    }

    @Override
    public void close() throws Exception {
        if (tThreadLocal != null){
            tThreadLocal.remove();
        }
    }

    public T closeAndGet(){
        if (tThreadLocal == null){
            return null;
        }
        T t = tThreadLocal.get();
        tThreadLocal.remove();
        return t;
    }
}
