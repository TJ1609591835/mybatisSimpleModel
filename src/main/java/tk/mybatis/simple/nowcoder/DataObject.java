package tk.mybatis.simple.nowcoder;

import java.io.Serializable;

public class DataObject implements Serializable {
    private static int i = 0;
    private String word = " ";

    public String getWord() {
        return word;
    }

    public void setWord(String word){
        this.word = word;
    }
    public void setI(int i){
        DataObject.i = i;
    }

    // psvm 快捷输出
    public static void main(String[] args) {
        DataObject object = new DataObject();
        object. setWord("123");
        object. setI(2);
        // 私有化的静态变量, 是可以修改值的
//        System.out.println(object.getWord()+", "+object.i);
        System.out.println(DataObject.i);
    }
}
