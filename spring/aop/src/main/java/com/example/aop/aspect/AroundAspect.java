package com.example.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

@Aspect
public class AroundAspect {
    @Pointcut("execution(int com.example.aop.calculator.MathCalculator.*(..))")
    public void pointCut() {
    }

    /**
     * 环绕通知固定写法如下：
     * Object: 返回值
     * ProceedingJoinPoint: 可以继续推进的切点
     */
    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs(); // 获取目标方法的参数

        // 前置
        System.out.println("环绕 - 前置通知：参数" + Arrays.toString(args));
        Object result;
        try {
            // proceed 方法接收目标方法的参数，可以实现修改目标方法执行用的参数
            result = pjp.proceed(args);// 继续执行目标方法; 反射 method.invoke()
            System.out.println("环绕 - 返回通知：返回值：" + result);
        } catch (Throwable e) {
            System.out.println("环绕 - 异常通知：" + e.getMessage());
            throw e;  // 让别人继续感知
        } finally {
            System.out.println("环绕 - 后置通知");
        }
        // 修改返回值
        return result;
    }
}
