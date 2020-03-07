package tk.mybatis.simple.test;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.BeforeClass;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ClassName: BaseMapperTest
 * Package: tk.mybatis.simple.test
 * created By taoJun
 * Description: 基础测试类
 *
 * @date: 2020/3/3 23:46
 * @author: taoJun
 * @email: 1609591835@qq.com
 */
public class BaseMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void init(){
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch(IOException ignore){
            ignore.printStackTrace();
        }
    }
    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
