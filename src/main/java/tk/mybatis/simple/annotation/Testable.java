package tk.mybatis.simple.annotation;

import java.lang.annotation.*;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
// 定义 Testable Annotation 将被 javadoc 工具提取
@Documented
public @interface Testable {
}
