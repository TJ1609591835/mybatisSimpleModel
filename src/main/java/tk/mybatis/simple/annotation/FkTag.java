package tk.mybatis.simple.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//指定该注解信息会保留到运行时
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FkTags.class)
public @interface FkTag {
    //为该注解定义2个成员变量
    String name() default "疯狂软件";
    int age();
}
