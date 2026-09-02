package com.example.aop.proxy;

import com.example.aop.calculator.MathCalculator;

// 静态代理：代理对象与目标对象实现同一接口，把接口作为代理对象的属性类型
// 在调用接口目标方法的前后插入额外逻辑。
// 优点是同类实现该接口的所有对象均可被代理；缺点是编码期就需确定代理关系，只能针对特定接口进行代理，功能范围有限
public class CalculatorStaticProxy implements MathCalculator {
    private MathCalculator mathCalculator; // 保存目标对象

    public CalculatorStaticProxy(MathCalculator mathCalculator) {
        this.mathCalculator = mathCalculator;
    }

    @Override
    public int add(int i, int j) {
        System.out.println("【日志】add 开始：参数：" + i + ", " + j);
        int result = mathCalculator.add(i, j);
        System.out.println("【日志】add 返回：结果：" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        int result = mathCalculator.sub(i, j);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = mathCalculator.mul(i, j);
        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = mathCalculator.div(i, j);
        return result;
    }
}
