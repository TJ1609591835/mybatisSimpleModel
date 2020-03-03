package tk.mybatis.simple.annotation;

@FkTag(age = 5)
@FkTag(name="疯狂 Java", age = 9)
//@FkTags({@FkTag(age=5), @FkTag(name="疯狂 Java", age=9)})
public class FKTagTest {
    public static void main(String[] args) {
        Class<FKTagTest>clazz=FKTagTest. class;
        /*使用Java8新增的 getpeclaredAnnotationgRyType（）方法获取修饰FkTagTest类的多个QFkTag 注解*/
        FkTag[]tags =clazz.getDeclaredAnnotationsByType(FkTag.class);
        //遍历修饰FkTagTest类的多个QFkTag 注解
        for(FkTag tag:tags) {
            System.out.println(tag.name() + "-->" + tag.age());
            /*使用传统的getDeclaredAnnotation（）方法获取修饰FkTagTest 类的QFkTags 注解*/
            FkTags container = clazz.getDeclaredAnnotation(FkTags.class);
            System.out.println(container);
        }
    }
}
