package tk.mybatis.simple.test.extend;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.test.BaseMapperTest;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {


    /**
     * 根据用户id 和角色的 enabled 状态获取用户的角色
     */
    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectRolesByUserIdAndRoleEnabled 方法查询用户的角色
            List<SysRole> userList =
                    roleMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            // 结果不为空
            Assert.assertNotNull(userList);
            // 角色数量大于 0 个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据id 查询Role 对象
     */
    @Test
    public void testSelectById(){
        // 获取 SqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectById 方法, 查询 id = 1 的角色
            SysRole role = roleMapper.selectById(1L);
            // role 不为空
            Assert.assertNotNull(role);
            // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据 id 获取 role 对象
     */
    @Test
    public void testSelectById2(){
        // 获取 SqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 selectById2 方法, 查询 id = 1 的角色
            SysRole role = roleMapper.selectById2(1L);
            // role 不为空
             Assert.assertNotNull(role);
             // roleName = 管理员
            Assert.assertEquals("管理员", role.getRoleName());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 查询所有 Role 对象
     */
    @Test
    public void testSelectAll(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            // 调用 SelectAll 方法查询所有角色
            List<SysRole> roleList = roleMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0 个
            Assert.assertTrue(roleList.size() > 0);
            // 验证下划线字段是否映射成功
            Assert.assertNotNull(roleList.get(0).getRoleName());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
     }

    /**
     * 测试添加 role 对象方法a
     */
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("testRoleName");
            sysRole.setEnabled(1L);
            sysRole.setCreateBy(1L);
            sysRole.setCreateTime(new Date());
            int result = roleMapper.insert(sysRole);
            Assert.assertEquals(1, result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
     }

    /**
     * 测试返回自增主键
     */
    @Test
    public void test2(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("testRoleName");
            sysRole.setEnabled(1L);
            sysRole.setCreateBy(1L);
            sysRole.setCreateTime(new Date());
            int result = roleMapper.insert2(sysRole);
            Assert.assertEquals(1, result);
            System.out.println(sysRole.getId());
        }finally{
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 测试注解修改方法
     */
    @Test
    public void testupdate(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectById(1L);
            Assert.assertEquals("管理员", sysRole.getRoleName());
            sysRole.setRoleName("管理员1");
            Assert.assertEquals(1, roleMapper.updateById(sysRole));
            Assert.assertEquals("管理员1", roleMapper.selectById(1L).getRoleName());
        }finally{
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try{
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Assert.assertEquals(1, roleMapper.deleteById(5L));
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
