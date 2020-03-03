package tk.mybatis.simple.test.extend;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.PrivilegeMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.test.BaseMapperTest;

public class PrivilegeMapperTest extends BaseMapperTest {
    /**
     * 根据id 查询privilege对象
     */
    @Test
    public void testSelectById(){
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 PrivilegeMapper 接口
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            // 调用 selectById 方法, 查询 id = 1 的权限
            SysPrivilege privilege = privilegeMapper.selectById(1L);
            // privilege不为空
            Assert.assertNotNull(privilege);
            // privilegeName = 用户管理
            Assert.assertEquals("用户管理", privilege.getPrivilegeName());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
