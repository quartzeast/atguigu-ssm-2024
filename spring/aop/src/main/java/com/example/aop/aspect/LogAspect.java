package com.example.aop.aspect;


import org.aspectj.lang.annotation.*;

@Aspect // 声明切面类
public class LogAspect {
    /*
        when: @Before, @After, @AfterReturning, @AfterThrowing
        where: 切入点表达式
        [public] int [xxx.xxx.MathCalculator].add(int,int) [throws ArithmeticException]
        通配符: *
    */

    // @Before("execution(int add(int, int))")
    @Before("execution(int *(int, int))")
    public void logStart() {
        System.out.println("LogAspect logStart");
    }

    @After("execution(int *(int, int))")
    public void logEnd() {
        System.out.println("LogAspect logEnd");
    }

    @AfterReturning("execution(int *(int, int))")
    public void logReturn() {
        System.out.println("LogAspect logReturn");
    }

    @AfterThrowing("execution(int *(int, int))")
    public void logException() {
        System.out.println("LogAspect logException");
    }
}