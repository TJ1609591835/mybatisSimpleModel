package tk.mybatis.simple.annotation;

// 使用 @Inheritable 修饰的 Base 类
@Inheritable
class Base{}
// InheritableTest 类只是继承了 Base 类
// 并未直接使用@Inheritable Annotation 修饰
public class InheritableTest extends Base {
    public static void main(String[] args) {
        // 打印 InheritableTest 类是否有@Inheritable 修饰
        System.out.println(InheritableTest.class
            .isAnnotationPresent(Inheritable.class));
    }
}
