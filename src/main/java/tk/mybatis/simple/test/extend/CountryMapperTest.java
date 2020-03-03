package tk.mybatis.simple.test.extend;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.test.BaseMapperTest;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * ClassName: CountryMapperTest
 * Package: tk.mybatis.simple.test.extend
 * created By taojun
 * Description: 城市表
 *
 * @date: 2020/3/3 23:46
 * @author: taojun
 * @email: 1609591835@qq.com
 */
public class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
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
                    country.getId(), country.getCountryName(), country.getCountryCode());
        }
    }
}
