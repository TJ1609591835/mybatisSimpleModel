package tk.mybatis.simple.IdentifiersAndKeywords;

public class Semicolon {
    // 符合
    int age = 25; String name = "李刚";
    String hello = "你好! "+
            "Java";

    // 不符合
        // 字符串不能跨越多行
        /*String a = "dddddd
                xxxxxx";*/
        // 变量名不能跨越多行
        /*String na
            me = "李刚";*/
}
