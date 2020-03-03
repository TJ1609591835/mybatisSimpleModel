package tk.mybatis.simple.annotation;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public class TypeAnnotationTest
        implements Serializable
{
    //方法形参中使用Type Annotation
    public static void main(@NotNull String[]args)
        //throws 时使用Type Annotation
        throws @NotNull FileNotFoundException
    {
        Object obj="fkjava.org";
        //强制类型转换时使用Type Annotation
        String str=(@NotNull String)obj;
        //创建对象时使用Type Annotation
//        Object win =new @NotNull JErame("疯狂软件");
    }

    //泛型中使用Type Annotation
    public void foo(List<@NotNull String> info){}
}
