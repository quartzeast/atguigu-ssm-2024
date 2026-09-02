package com.example.aop.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynamicProxy {
    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("[BEGIN] " + "method name: " +
                            method.getName() + ", args: " + Arrays.toString(args));
                    Object result = method.invoke(target, args);
                    System.out.println("[END] " + "result: " + result);
                    return result;
                });
    }
}
