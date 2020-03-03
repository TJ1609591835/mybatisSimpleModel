package tk.mybatis.simple.nowcoder;

/**
 * @author taojun
 */
public class Xue {
    public static void main(String[] args) {
//        测试i++和++i的区别
//        whileTest();

//        生成静态乘法表
//        multiplicationTable();

//        简单阶乘例子
          factorial();
    }

    /**
     * 测试i++和++i的区别
     */
    private static void whileTest(){
        System.out.println("==========while Test==========");
        int x = 10;
        while (x < 20) {
            System.out.println("value of x : " + x);
            x++;
            System.err.println("x++后的值: " + x);
            System.out.print("\n");
        }

        System.out.println("==========Do While Test==========");
        x = 10;
        do {
            System.out.println(x++);
//            System.out.println(++x);
            if (x % 2 == 0) {
                continue;
            }
            System.out.println("value of x : " + x);
        } while (x < 20);
    }

    /**
     * 生成乘法表
     */
    private static void multiplicationTable(){
        for(int i = 1; i < 10; i++){
            for(int j = 1; j <= i; j++){
                System.out.print(i + " * " + j + " = " + i*j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 简单阶乘例子
     */
    private static void factorial(){
        int a = 5;
        int b = factorialCalculation(a);
        System.out.println("阶乘结果: " + b);
    }

    private static int factorialCalculation(int m){
        if (m > 1) {
            return m * factorialCalculation(m - 1);
        } else {
            return 1;
        }
    }

    /**
     * 重载函数
     */
    private static int add(int m, int n){
        return m + n;
    }

    private static double add(double m, double n){// 和上一个方法重载
        return m + n;
    }


}
