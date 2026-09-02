package com.example.aop.proxy;

import com.example.aop.utils.LogUtil;

import java.lang.reflect.Proxy;

public class LogDynamicProxy {
    // 获取目标对象的代理对象
    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    String name = method.getName();
                    // 记录开始
                    LogUtil.logStart(name, args);
                    Object result = null;
                    try {
                        result = method.invoke(target, args);
                        // 记录返回值
                        LogUtil.logReturn(name, result);
                    } catch (Exception e) {
                        // 记录异常
                        LogUtil.logException(name, e);
                    } finally {
                        // 记录结束
                        LogUtil.logEnd(name);
                    }
                    return result;
                }
        );
    }
}
