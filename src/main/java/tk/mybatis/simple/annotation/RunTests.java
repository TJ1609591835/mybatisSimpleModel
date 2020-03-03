package tk.mybatis.simple.annotation;

import tk.mybatis.simple.annotation.ProcessorTest;

import java.util.List;

public class RunTests {
    public static void main(String[] args)
            throws Exception{
        // 处理MyTest 类
        ProcessorTest.process("tk.mybatis.simple.annotation.MyTest");
    }
}
