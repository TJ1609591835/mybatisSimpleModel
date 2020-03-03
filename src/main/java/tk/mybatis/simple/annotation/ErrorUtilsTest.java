package tk.mybatis.simple.annotation;

import java.util.Arrays;

public class ErrorUtilsTest {
    public static void main(String[] args){
        ErrorUtils.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));

        /*ErrorUtils errorUtils = new ErrorUtils();
        errorUtils.test();*/

        // lombok - @Builder
    }
}
