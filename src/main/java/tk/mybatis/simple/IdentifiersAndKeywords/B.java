package tk.mybatis.simple.IdentifiersAndKeywords;

/**
 * 子类
 * @author taojun
 */
public class B extends A {
    public B(){
        System.out.println("33333333");
    }
    public B(int a){
        System.out.println("44444444");
    }

    /**
     * super的时候调用父类的构造方法入参是什么, 调用父类的构造方法构造方法只要存在对应参数, 那么调用该构造方法, 反之调用父类无参构造
     * @param args
     */
    public static void main(String[] args) {
        B obj1 = new B();
        System.out.println("=============");
        B obj2 = new B(2);
    }

}
