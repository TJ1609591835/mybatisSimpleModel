package tk.mybatis.simple.util;

/**
 * @author taojun
 */
public class StringUtil {
    public static boolean isEmpty(String str){
        return str == null | str.length() == 0;
    }
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
    public static void print(Object parameter){
        System.out.println(parameter);
        // xml中可以使用
        // <bind name="print" value="@tk.mybatis.simple.util.StringUtil@print(_parameter)"/>
        // 有些情况下可能会起到非常好的效果, 但是要避免卵用, 以免给其他人造成混乱
    }
}
