package com.example.aop;

import com.example.aop.calculator.MathCalculator;
import com.example.aop.calculator.impl.MathCalculatorImpl;
import com.example.aop.proxy.CalculatorStaticProxy;
import com.example.aop.proxy.DynamicProxy;
import com.example.aop.proxy.LogDynamicProxy;
import com.example.aop.service.UserService;
import com.example.aop.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

@SpringBootTest
class AopApplicationTests {

    @Autowired
    MathCalculator mathCalculator;

    @Test
    void contextLoads() {
    }

    // 实验 1：硬编码与静态代理
    @Test
    void aopTest01() {
        MathCalculator mathCalculator = new MathCalculatorImpl();
        System.out.println(mathCalculator.add(1, 2));

        // 1. 创建静态代理对象
        MathCalculator proxy = new CalculatorStaticProxy(mathCalculator);
        int add = proxy.add(1, 2);
        System.out.println(add);
    }

    // 实验 2：动态代理
    @Test
    void aopTest02() {
        // 1. 目标对象
        MathCalculator target = new MathCalculatorImpl();

        InvocationHandler h = new InvocationHandler() {
            /**
             * proxy: 代理对象
             * method: 代理对象准备调用目标对象的方法
             * args: 方法调用传递的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("invocationHandler's invoke running...");
                System.out.println("args before: " + Arrays.asList(args));

                args[1] = 0; // 改变参数

                System.out.println("args after: " + Arrays.asList(args));

                Object result = method.invoke(target, args);

                // return 0; // 改变返回值
                return result;
            }
        };

        // 2. 创建动态代理（动态代理 target，指定 InvocationHandler，target 的目标方法执行都会经过其进行包装）
        MathCalculator proxyInstance = (MathCalculator) Proxy.newProxyInstance( // 注意这里需要进行一次强转
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                h
        );

        var result = proxyInstance.add(1, 2); // 执行之前拦截
        System.out.println(result);
    }


    // 实验 3：动态代理加日志
    @Test
    void aopTest03() {
        MathCalculator proxyInstance = (MathCalculator) DynamicProxy.createProxy(new MathCalculatorImpl());
        proxyInstance.add(1, 2);

        UserService userService = (UserService)  DynamicProxy.createProxy(new UserServiceImpl());
        userService.saveUser();
    }

    // 实验 4；日志工具代理类
    @Test
    void aopTest04() {
        MathCalculator proxyInstance = (MathCalculator) LogDynamicProxy.getProxyInstance(new MathCalculatorImpl());
        proxyInstance.sub(1, 2);

        UserService userService = (UserService)  LogDynamicProxy.getProxyInstance(new UserServiceImpl());
        userService.saveUser();
    }
}
