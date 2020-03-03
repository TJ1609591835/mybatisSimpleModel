package tk.mybatis.simple.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;

public class MyTest {
    // 使用 @Testable 修饰 info() 方法
    @Testable
    @org.junit.Test
    public void info(){
        System.out.println("info方法...");
    }

    // 使用@Testable 注解指定该方法是可测试的
    @Testable
    public static void m1(){}

    public static void m2(){}

    // 使用 @Testable 注解指定该方法是可测试的
    @Testable
    public static void m3(){
        int a = 1/0;
//        throw new IllegalArgumentException("参数出错了! ");
    }

    public static void m4(){}

    // 使用@Testable 注解指定该方法是可测试的
    @Testable
    public static void m5(){}

    public static void m6(){}

    // 使用@Testable注解指定该方法是可测试的
    @Testable
    public static void m7(){
        throw new RuntimeException("程序业务出现异常!");
    }
    public static void m8(){}

   /* public static void main(String[] args) throws Exception {
        //获取Test类的info方法的所有注解
        Annotation[] aArray = Class.forName("tk.mybatis.simple.annotation.MyTest").getMethod("info").getAnnotations();
        //遍历所有注解
        for(Annotation an :aArray )
        {
            System.out.println(an);
        }
    }*/
}
