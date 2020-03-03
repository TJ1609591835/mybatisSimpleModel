package tk.mybatis.simple.annotation;

public class Fruit
{
    public void info()
    {
        System.out.println("水果的 info 方法...");
    }
}
class Apple1 extends Fruit
{
    /**
     * 使用@Override 指定下面方法必须重写父类方法
     */
    @Override
    public void info()
    {
        System.out.println("苹果重写水果的 info 方法......");
    }
}
