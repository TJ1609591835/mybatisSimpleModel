package tk.mybatis.simple.annotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// 关闭整个类里的编译器警告
// 注: @SuppressWarnings 会一直作用与该程序元素的所有子元素

public class SuppressWarningsTest {

    /**
     * 堆污染: 当把一个不带泛型的对象赋给一个带泛型的变量时, 就会发生
     */
    @SuppressWarnings(value="unchecked")
    /*@SafeVarargs*/
    public static void main(String[] args) {
        List<String> myList = new ArrayList();// (1)
//        System.out.println("test1");
        List list = new ArrayList<Integer>();
        list.add(20);// 添加元素时引发 unchecked 异常
        // 下面代码引起"未经检查的转换" 的警告, 编辑、运行时完全正常
        List<String> ls = list;
        // 但只要访问 ls 里的元素, 如下代码
        System.out.println(ls.get(0));
    }
}
