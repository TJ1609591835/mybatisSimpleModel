package tk.mybatis.simple.annotation;

@FunctionalInterface
public interface FunInterface
{
    static void foo(){
        System.out.println("foo 类方法");
    }
    default void bar(){
        System.out.println("bar 默认方法");
    }

   static void staticMethod(){}
    void test();// 只定义一个抽象方法

//    void test1(); // 多定义就报错
}
