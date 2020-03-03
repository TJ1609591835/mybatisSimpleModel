package tk.mybatis.simple.test.extend;
import org.junit.Test;
import tk.mybatis.simple.dataSource.MyMapperProxy;
import tk.mybatis.simple.mapper.RoleMapper;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.test.BaseMapperTest;
import tk.mybatis.simple.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import tk.mybatis.simple.model.SysUser;
import org.junit.Assert;

import java.lang.reflect.Proxy;
import java.sql.SQLOutput;
import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class UserMapperTest extends BaseMapperTest {
//    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 根据id查询数据
     */
    @Test
    public void testSelectById(){
        // 获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try{
            // 获取UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectById 方式, 查询id = 1 的用户
            SysUser user = userMapper.selectById(1L);
            // user不为空
            Assert.assertNotNull(user);
            // userName = admin
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 查询全部数据
     */
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectAll 方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(userList);
            // 用户数量大于0 个
            Assert.assertTrue(userList.size() > 0);

            for(SysUser sysUser : userList){
                System.out.println("id:"+sysUser.getId());
                System.err.println("userName:"+sysUser.getUserName());
            }
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据用户对象查询角色对象
     */
    @Test
    public void testSelectRolesByUserId(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectRolesByUserId 方法查询用户的角色e
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            // 结果不为空
            Assert.assertNotNull(roleList);
            // 角色数量大于0 个
            Assert.assertTrue(roleList.size() > 0);

        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 添加方法1
     */
    @Test
    public void testInsert(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("testTJ1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            // 正常情况下应该读入一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});// byteArrayInputStream--> BLOB 二进制数据流
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中, 特别注意这里的返回值 result是执行的 SQL影响的行数
            int result = userMapper.insert(user);
            // 只插入 1 条数据
            Assert.assertEquals(1, result);
            // id为 null, 没有给 id 赋值, 并且没有配置回写 id 的值
            Assert.assertNull(user.getId());
        } finally {
            // 为了不影响其他测试, 这里选择回滚
            // 由于默认的 sqlSessionFactory.openSession() 是不自动提交的
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            /*sqlSession.commit();*/
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 添加方法2
     */
    @Test
    public void testInsert2(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test info");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userMapper.insert2(user);
            // 只插入1条数据
            Assert.assertEquals(1, result);
            // 因为id 回写, 所以id 不为null
            Assert.assertNotNull(user.getId());
        }finally{
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据主键更新
     */
    @Test
    public void testUpdateById(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库查询 1 个 user 对象
            SysUser user = userMapper.selectById(1L);
            // 当前 userName 为 admin
            Assert.assertEquals("admin", user.getUserName());
            // 修改用户名
            user.setUserName("admin_test");
            // 修改邮箱
            user.setUserEmail("test@mybatis.tk");
            // 更新数据, 特别注意, 这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.updateById(user);
            // 只更新 1 条数据
            Assert.assertEquals(1, result);
            // 根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            // 修改后的名字是 admin_test
            Assert.assertEquals("admin_test", user.getUserName());
        } finally {
            // 为了不影响其他测试, 这里选择回滚
            // 由于默认的 sqlSessionFactory.openSession() 是不自动提交的,
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库查询 1 个 user 对象, 根据 id = 1 查询
            SysUser user1 = userMapper.selectById(1L);
            // 现在还能查询出 user 对象
            Assert.assertNotNull(user1);
            // 调用方法删除
            Assert.assertEquals(1, userMapper.deleteById(1L));
            // 再次查询, 这时应该没有值, 为 null
            Assert.assertNull(userMapper.selectById(1L));

            // 使用 SysUser 参数在进行一次测试, 根据 id = 1001 查询
            SysUser user2 = userMapper.selectById(1001L);
            // 现在还能查询出 user 对象
            Assert.assertNotNull(user2);
            // 调用方法删除, 注意这里使用参数为 user2
            Assert.assertEquals(1, userMapper.deleteById(1001L));
            // 使用 SysUser 参数再进行一次测试
        } finally {
            // 为了不影响其他测试, 这里选择回滚
            // 由于默认的 sqlSessionFactory.openSesssion() 是不自动提交的,
            // 因此不手动执行 commit 也不会提交到数据库
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 动态代理
     */
    @Test
    public void MyMapperProxy(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        // 获取 UserMapper 接口
        MyMapperProxy userMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},
                userMapperProxy
        );
        // 调用 selectAll 方法
        List<SysUser> user = userMapper.selectAll();
    }

    /**
     * 根据动态条件查询用户信息
     */
    @Test
    public void testSelectByUser(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 只查询用户名
            SysUser query = new SysUser();
            query.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            // 只查询用户邮箱时
            query = new SysUser();
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            Assert.assertTrue(userList.size() > 0);
            // 当同时查询用户名和邮箱时
            query = new SysUser();
            query.setUserName("ad");
            query.setUserEmail("test@mybatis.tk");
            userList = userMapper.selectByUser(query);
            // 由于没有同时符合这两个条件的用户, 因此查询结果数为 0
            Assert.assertTrue(userList.size() == 0);
        }finally{
            sqlSession.rollback();
            // 不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据主键更新
     * 使用if标签
     */
    @Test
    public void testUpdateByIdSelective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个新的 user 对象
            SysUser user = new SysUser();
            // 更新id = 1的用户
            user.setId(1L);
            // 修改邮箱
            user.setUserEmail("test@mybatis.tk");
            // 更新邮箱, 特别注意, 这里的返回值 result 执行的是 SQL 影响的行数
            int result = userMapper.updateByIdSelective(user);
            // 只更新 1 条数据
            Assert.assertEquals(1, result);
            // 根据当前 id 查询修改后的数据
            user = userMapper.selectById(1L);
            // 修改后的名字保持不变 , 但是邮箱变成了新的
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        }finally{
            // 为了不影响其他测试, 这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 插入数据
     * 使用数据库默认值
     */
    @Test
    public void testInsert2Selective(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user 对象
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123456");
            user.setUserInfo("test info");
            user.setCreateTime(new Date());
            // 插入数据库
            userMapper.insert2ByLabel(user);
            // 获取插入的这条数据
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据用户id或用户名查询
     */
    @Test
    public void testSelectByIdOrUserName(){
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 只查询用户名时
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUserName("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 当没有 id 时
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            // 当 id 和 name 都为空
            query.setUserName(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }finally{
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据用户 id 集合查询
     */
    @Test
    public void testSelectByIdList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 测试collection = list
            /*List<Long> idList = new ArrayList<Long>();
            idList.add(1L);
            idList.add(1001L);*/
            // 测试collection = array
            /*Long[] l1 = new Long[2];
            l1[0] = 1L;
            l1[1] = 1001L;*/
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("test1", 1L);
            map.put("test2", 1001L);
            // 业务逻辑中必须校验 idList.size() > 0
            List<SysUser> userList = userMapper.selectByIdList(map);
            Assert.assertEquals(2, userList.size());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 测试批量添加
     * 注: 需要注意此功能需要数据库支持
     * 注: select...union all select...(不太安全)
     */
    @Test
    public void testInsertList(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user 对象
            List<SysUser> userList = new ArrayList<SysUser>();
            for(int i = 0; i < 2; i++){
                SysUser user = new SysUser();
                user.setUserName("test" + i);
                user.setUserPassword("123456");
                user.setUserEmail("test@mybatis.tk");
                userList.add(user);
            }
            // 将新建的对象批量插入数据库中
            // 特别注意, 这里的返回值是 result 是执行 SQL 影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);
            // 循环输出插入数据的id回显的值
            for(SysUser user : userList){
                System.out.println(user.getId());
            }
        }finally{
            // 为了不影响其他测试, 这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 在xml中循环map
     */
    @Test
    public void testUpdateByMap(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询条件, 同样也是更新字段, 必须保证该值存在
            map.put("id", 1L);
            // 要更新的其他字段
            map.put("user_email","test@mybatis.tk");
            map.put("user_password", "12345678");
            // 更新数据
            userMapper.updateByMap(map);
            // 根据当前 id 查询修改后的数据
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", user.getUserEmail());
        } finally {
            // 为了不影响其他测试, 这里选择回滚
            sqlSession.rollback();
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 根据用户id 获取用户信息和用户的角色信息
     */
    @Test
    public void testSelectUserAndRoleById(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try{
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 特别注意, 在测试数据中, id=1L的用户有两个角色, 不适合这个例子
            // 这里使用只有一个角色的用户 (id=1001L)
//            SysUser user = userMapper.selectUserAndRoleById(1001L);
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            // user 不为空
            Assert.assertNotNull(user);
            // user.role 也不为空
            Assert.assertNotNull(user.getRole());
        }finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 测试association的select属性
     */
    @Test
    public void testSelectUserAndRoleByIdSelect(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 这里使用只有一个角色的用户 (id = 1001L)
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            // user 不为空
            Assert.assertNotNull(user);
            // user.role 也不为空
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getRole());
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 获取所有的用户以及对应的所有角色
     */
    @Test
    public void testSelectAllUserAndRoles(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数: " + userList.size());
            for(SysUser user: userList){
                System.out.println("用户名: " + user.getUserName());
                for(SysRole role : user.getRoleList()){
                    System.out.println("角色名: " + role.getRoleName());
                }
            }
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 获取所有的用户以及对应的所有角色
     */
    @Test
    public void testSelectAllUserAndRoles2(){
        // 获取 sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList = userMapper.selectAllUserAndRoles2();
            System.out.println("用户数: " + userList.size());
            for(SysUser user: userList){
                System.out.println("用户名: " + user.getUserName());
                for(SysRole role : user.getRoleList()){
                    System.out.println("角色名: " + role.getRoleName());
                    for(SysPrivilege privilege : role.getPrivilegeList()){
                        System.out.println("权限名: = " + privilege.getPrivilegeName());
                    }
                }
            }
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 为了加深印象, 专门通过角色查询对应的权限
     */
    @Test
    public void testSelectAllRoleAndPrivileges(){
        // 获取 SqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            // 获取 RoleMapper 接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();

            roleList.stream().forEach(System.out::print);
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }

    /**
     * 通过main方法来执行mybatis
     */
    /*public void testSelectAll(){
        SqlSession sqlSession = null;
        try{
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }catch(IOException ignore){
            ignore.printStackTrace();
        }
        try{
            sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectAll 方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(userList);
            // 用户数量大于0 个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
    public static void main(String[] args){
        UserMapperTest umt = new UserMapperTest();
        umt.testSelectAll();
    }*/



}
