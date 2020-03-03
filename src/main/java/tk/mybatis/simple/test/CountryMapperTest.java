package tk.mybatis.simple.test;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;
import tk.mybatis.simple.model.Country;

public class CountryMapperTest {
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

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try{
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        }finally{
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for(Country country : countryList){
            System.out.printf("%-4d%4s%4s\n",
                    country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
