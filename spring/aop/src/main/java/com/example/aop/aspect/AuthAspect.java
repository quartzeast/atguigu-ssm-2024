package com.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Order(100)
@Aspect
public class AuthAspect {
    @Pointcut("execution(int com.example.aop.calculator.MathCalculator.*(..))")
    public void pointCut() {}


}
