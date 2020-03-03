package tk.mybatis.simple.annotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ErrorUtils {

    @SafeVarargs
    public static void faultyMethod(List<String>... listStrArray)
    {
        // Java 语言不允许创建泛型数组, 因此 listArray 只能被当成 List[] 处理
        // 此时相当于把 List<String> 赋给了 List, 已经发生了"堆污染"
        List[] listArray = listStrArray;
        // 网上的范型数组概念: List<String>[] ls = new ArrayList<String>[10];   这个是不行的;   List<String>[] ls = new ArrayList[10];
        // 个人理解:  相当于我们的List<List<String>>
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(new Random().nextInt(100));
        // 当 listArray 的第一个元素赋给 myArray
        listArray[0] = myList;
        // 缺少获取值
        String s = listStrArray[0].get(0);
    }

    // 为什么不支持泛型数组
    // Sun say
    @Test
    /*@SuppressWarnings(value="unchecked")*/
    public void test(){
        List<?>[] lsa = new List<?>[10];// OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li;// Correct.
//        System.out.println(li.get(0));
        // 取不出来
        String s = (String) lsa[1].get(0);// Run time error, but case is explicit
    }
}
