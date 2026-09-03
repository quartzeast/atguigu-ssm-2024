package com.example.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Order(10000) // 数字越小，优先级越高，数字越大，优先级越低; 优先级越高，越先执行
@Aspect
public class LogAspectV2 {
    @Pointcut("execution(int com.example.aop.calculator.MathCalculator.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void logBefore(JoinPoint joinPoint) {
        // 1. 拿到方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        // 方法名
        String name = signature.getName();

        // 目标方法传来的参数值
        Object[] args = joinPoint.getArgs();

        System.out.println("【切面 - 日志】【" + name + "】开始：参数列表：【" + Arrays.toString(args) + "】");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("【切面 - 日志】【" + name + "】后置...");
    }

    @AfterReturning(value = "pointCut()", returning = "result") // returning="result" 获取目标方法返回值
    public void logReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String name = signature.getName();

        System.out.println("【切面 - 日志】【" + name + "】返回：值：" + result);
    }

    @AfterThrowing(
            value = "pointCut()",
            throwing = "e" // throwing="e" 获取目标方法抛出的异常
    )
    public void logException(JoinPoint joinPoint, Throwable e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();

        System.out.println("【切面 - 日志】【" + name + "】异常：错误信息：【" + e.getMessage() + "】");
    }

    // args
    @Before("args(int, int)")
    public void logArgs() {
        System.out.println("【切面 - 日志】args(int, int)");
    }

    @Before("@annotation(com.example.aop.annotation.MyAnnotation)")
    public void logAnnotation() {
        System.out.println("【切面 - 日志】@MyAnnotation");
    }

    @Before("@args(com.example.aop.annotation.MyAnnotation) && within(com.example.aop.service.UserService)")
    public void test() {}
}
