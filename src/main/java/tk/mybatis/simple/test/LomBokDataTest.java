package tk.mybatis.simple.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LomBokDataTest {

    private Long id;
   /* private String name;
    private Integer age;
    private String email;*/

    public static void main(String[] args) {
        LomBokDataTest lomBokDataTest = LomBokDataTest.builder().id(12L).build();
    }

}
