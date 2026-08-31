package com.example.ioc;

import ch.qos.logback.core.CoreConstants;
import com.example.ioc.bean.*;
import com.example.ioc.controller.AccountController;
import com.example.ioc.controller.BookController;
import com.example.ioc.controller.DeviceController;
import com.example.ioc.controller.ProductController;
import com.example.ioc.controller.UserController;
import com.example.ioc.dao.DeliveryDao;
import com.example.ioc.repository.UserRepository;
import com.example.ioc.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        // springTest01(args);
        // springTest02(args);
        // springTest03(args);
        // springTest04(args);
        // springTest05(args);
        // springTest06(args);
        // springTest07(args);
        // springTest08(args);
        // springTest09(args);
        // springTest10(args);
        // springTest11(args);
        // springTest12(args);
        // springTest13(args);
        // springTest14(args);
        // springTest15(args);
        // springTest16(args);
        // springTest17(args);
        springTest18(args);
    }

    // 实验 1：启动 Spring 容器，通过 @Bean 注册组件到容器中
    public static void springTest01(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        System.out.println("ioc context =" + context);

        // 获取 spring 中的所有组件的名称，Spring 默认注册了大量的组件
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("bean name = " + name);
        }
    }


    // 给容器中注册一个组件，方法名默认为组件名
    @Bean
    public Person rockman() {
        Person person = new Person();
        person.setName("rockman");
        person.setAge(30);
        person.setAddress("somewhere in the world.");
        return person;
    }

    @Bean
    public Person quartz() {
        Person person = new Person();
        person.setName("quartz");
        person.setAge(22);
        person.setAddress("somewhere in the world.");
        return person;
    }

    // 实验 2：从容器中获取组件的四种方式
    public static void springTest02(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        /*
            Spring IoC 容器中组件的四大特征：名称、类型、作用域、对象
            从容器中获取组件有四种方式：
            1. 按组件名获取单个 getBean("name")
            2. 按类型获取单个 getBean(Class)
            3. 按类型获取多个 getBeansOfType(Class)
            4. 按名称 + 类型精确获取单个 getBean(name, Class) - 无需强转类型

            组件名全局唯一，如果重复，则容器中只存在先声明的那个组件
         */

        // 1. 按组件名获取（组件名在容器中是唯一的）
        // 按类型或按名称获取组件时，若组件不存在抛 NoSuchBeanDefinitionException
        Person rockman = (Person) context.getBean("rockman");
        System.out.println("rockman = " + rockman);

        // 2. 按组件类型获取
        // 按类型获取组件时，如果同类型组件有多个则抛 NoUniqueBeanDefinitionException
        // Person person = context.getBean(Person.class);
        // ComputerSystem.out.println("person = " + person);

        // 3. 按照类型获取该类型所有组件
        Map<String, Person> beans = context.getBeansOfType(Person.class);
        System.out.println("beans = " + beans);

        // 4. 按照名称和类型精确获取组件
        Person quartz = context.getBean("quartz",  Person.class);
        System.out.println("quartz = " + quartz);
    }

    // 实验 3：组件的创建时机和单例特性
    public static void springTest03(String[] args) {
        // 单例组件的创建是在容器启动过程中完成的，对象创建后驻留在容器中
        // 对象创建后驻留在容器中，后续无论通过容器获取多少次，返回的都是同一个实例对象，构造器仅在容器启动时调用一次
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        System.out.println("--- spring ioc 容器启动完毕 ---");

        // 组件具有单例特性，默认情况下 Spring 容器中所有组件都是单例的
        Dog dog01 = context.getBean(Dog.class);
        Dog dog02 = context.getBean(Dog.class);
        System.out.println("dog01 = " + dog01);
        System.out.println("dog02 = " + dog02);
        System.out.println(dog01 == dog02); // true
    }

    // 实验 4：MVC 分层注解
    public static void springTest04(String[] args) {
        // Spring 为 MVC 分层组件提供了快捷注解，可以直接将各层组件注入 IOC 容器中，无需在配置类中逐个通过 @Bean 注册
        // @Controller, @Service, @Repository, @Component
        // 分层组件起作用的前提是，这些组件必须位于主程序所有在的包及其子包下
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        UserController userController = context.getBean(UserController.class);
        System.out.println("userController = " + userController);

        UserService userService = context.getBean(UserService.class);
        System.out.println("userService = " + userService);

        UserRepository userRepository = context.getBean(UserRepository.class);
        System.out.println("userRepository = " + userRepository);
    }

    // 实验 5：@ComponentScan 和 @Import
    public static void springTest05(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        CoreConstants coreConstants = context.getBean(CoreConstants.class);
        System.out.println("coreConstants = " + coreConstants);
    }

    // 实验 6：@Scope 和 @Lazy，调整组件作用域 + 懒加载
    public static void springTest06(String[] args) {
        /*
            @Scope 调整组件的作用域
            1. @Scope("prototype") - 非单例
                容器启动时不创建非单例对象，获取时才创建
            2. @Scope("singleton") - 单例、默认
            3. @Scope("request")
            4. @Scope("session")

            @Lazy 懒加载
                单例组件的创建延迟到获取时才创建对象，懒加载只对单例模式生效
         */
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        System.out.println("--- spring ioc 容器启动完毕 ---");

        // 容器创建时（完成之前）就把所有单例对象创建完成了
        Object luna1 = context.getBean("luna");
        Object luna2 = context.getBean("luna");
        System.out.println(luna1 == luna2); // true

        Object anna1 = context.getBean("anna");
        Object anna2 = context.getBean("anna");
        System.out.println(anna1 == anna2); // false

        Object jersey1 = context.getBean("jersey");
        Object jersey2 = context.getBean("jersey");
        System.out.println(jersey1 == jersey2); // true
    }

    // 实验 7: FactoryBean
    public static void springTest07(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        // FactoryBean 在容器中注册的组件类型，是接口中泛型指定的类型
        // 组件的名称是「工厂的名称」
        Car car1 = context.getBean(Car.class);
        Car car2 = context.getBean(Car.class);
        System.out.println(car1 == car2);

        Map<String, Car> cars = context.getBeansOfType(Car.class);
        System.out.println("cars = " + cars);
    }

    // 实验 8: 条件注册
    public static void springTest08(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        Map<String, OS>  computerSystems = context.getBeansOfType(OS.class);
        System.out.println("computerSystems = " + computerSystems);

        // 获取环境变量
        ConfigurableEnvironment environment = context.getEnvironment();

        String os = environment.getProperty("OSConfig");
        System.out.println("property = " + os);

        OS cs = context.getBean(OS.class);
        System.out.println("cs = " + cs);
    }

    // 实验 9：@Autowired - 自动注入
    public static void springTest09(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        AccountController accountController = context.getBean(AccountController.class);
        System.out.println("accountController = " + accountController);
    }

    // 实验 10：@Qualifier 和 @Primary
    public static void springTest10(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        BookController bookController = context.getBean(BookController.class);
        System.out.println("primaryBook = " + bookController.getPrimaryBook());
        System.out.println("javaBook = " + bookController.getJavaBook());
    }

    // 实验 11：@Resource 自动注入
    public static void springTest11(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        BookController bookController = context.getBean(BookController.class);
        System.out.println("mysqlBook = " + bookController.getMysqlBook());
        System.out.println("goBook = " + bookController.getGoBook());
    }

    // 实验 12：Aware 感知接口
    public static void springTest12(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        DeviceController deviceController = context.getBean(DeviceController.class);
        System.out.println("device = " + deviceController.getDevice());
    }

    // 实验 13：构造器注入和 setter 注入
    public static void springTest13(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        ProductController productController = context.getBean(ProductController.class);
        System.out.println("product = " + productController.getProduct());
        System.out.println("category = " + productController.getCategory());
    }

    // 实验 14：@Value
    public static void springTest14(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        Cat cat = context.getBean(Cat.class);
        System.out.println("cat = " + cat);
    }

    // 实验 15: @PropertySource
    public static void springTest15(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        Fish fish = context.getBean(Fish.class);
        System.out.println("fish = " + fish);
    }

    // 实验 16：ResourceUtils 获取资源
    public static void springTest16(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        System.out.println("context = " + context);

        try {
            File file = ResourceUtils.getFile("classpath:img.png");
            System.out.println("file = " + file);

            int available = new FileInputStream(file).available();
            System.out.println("available = " + available);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 实验 17：@Profile 多环境条件注册
    public static void springTest17(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);

        DeliveryDao dao = context.getBean(DeliveryDao.class);
        dao.saveDelivery();
    }

    // 实验 18：生命周期
    public static void springTest18(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IocApplication.class, args);
        System.out.println("--- spring ioc 容器启动完毕 ---");

        Driver driver = context.getBean(Driver.class);
        System.out.println("driver = " + driver);
    }

}
