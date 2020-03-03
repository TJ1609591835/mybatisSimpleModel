package tk.mybatis.simple.test;

import lombok.*;
import org.junit.Test;

import java.io.*;

/**
 * @author taojun
 */
@AllArgsConstructor
@NoArgsConstructor
/*@Builder*/


@Getter
@Setter
@Data
public class LomBokTest  implements Serializable {
    private static final Object $LOCK = new Object[0];

    private transient String name;
    private String age;

    /*public LomBokTest (String age) {

    }*/

    public static void main(String[] args) throws Exception {

        LomBokTest a = new LomBokTest();
        a.getAge();
        a.setAge("123");

       /* @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);

        byte[] b = null;

        while (true) {
            b = new byte[1024];
            int r = in.read(b);
            if(r == -1) { break; }
            out.write(b, 0, r);
        }*/


//        LomBokTest a = new LomBokTest(null);
//        LomBokTest lomBokTest = LomBokTest.builder().name("嘤").age("123").name("测试").build();
//        System.out.println("lomBokTest = " + lomBokTest.name);
//        System.out.println("lomBokTest = " + lomBokTest.age);

        // 测试 @NonNull
        /*LomBokTest lomBokTest = new LomBokTest();
        lomBokTest.testNonNull (null);*/
    }

    @Test
    public void main() {
        try {

            /*String a1 = null;*/
            testNonNull (null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试 NonNull
     */
    private void testNonNull (@NonNull String a1) {
        System.out.println("scuess @NonNull!");
    }

    /*public LomBokTest () {

    }*/
}
