package tk.mybatis.simple.annotation;

class Apple {
    /**
     * 定义 info 方法已过时
     */
    @Deprecated
    public void info(){
        System.out.println("Apple 的 info 方法");
    }
}
public class DeprecatedTest
{
    public static void main(String[] args) {
        // 下面使用 info() 方法时将会被编译器警告
        new Apple().info();
    }
}
