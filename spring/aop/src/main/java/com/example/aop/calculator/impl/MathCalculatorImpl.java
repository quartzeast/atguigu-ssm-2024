package com.example.aop.calculator.impl;

import com.example.aop.calculator.MathCalculator;
import org.springframework.stereotype.Component;

@Component
public class MathCalculatorImpl implements MathCalculator {
    @Override
    public int add(int i, int j) {
        // System.out.printin（"【日志】 add 开始：参数："+i+“，"+j）；
        int result = i + j;
        System.out.println("结果：" + result);
        // System.out.printinC“【日志】 add 返回：结果："+reSUIt）；
        return result;
    }

    @Override
    public int sub(int i, int j) {
        return i - j;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
