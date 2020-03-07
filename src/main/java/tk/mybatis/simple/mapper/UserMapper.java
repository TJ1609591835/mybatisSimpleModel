package tk.mybatis.simple.mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 通过id 查询用户
     * 注: 通过主键查询只会返回一条数据
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);

    /**
     * 查询全部用户
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户id 获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);
    /*SysUser selectRolesByUserId(Long userId);*/

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户-使用 useGeneratedKeys 方式
     *
     * 注: 适用于自增型数据库, 如MySQL
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户-使用 selectKey 方式
     *
     * 注: 适用于序列型数据库, 如Oracle
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
    // int deleteById(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 添加数据, 测试数据库默认值
     *
     * @param sysUser
     * @return
     */
    int insert2ByLabel(SysUser sysUser);

    /**
     * 根据用户 id 或用户名查询
     *
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据动态条件查询用户信息
     * 使用where 标签
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUserByWhereLabel(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelectiveByWhereLabel(SysUser sysUser);

    /**
     * 根据用户 id 集合查询
     *
     * @param idList
     * @param idArray
     * @param map
     * @return
     */
//    List<SysUser> selectByIdList(List<Long> idList);
//    List<SysUser> selectByIdList(Long[] idArray);
    List<SysUser> selectByIdList(@Param("map")Map<String, Object> map);

    /**
     * 批量插入用户信息
     *
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);

    /**
     * 通过Map 更新列
     *
     * @param map
     * @return
     */
    int updateByMap(Map<String, Object> map);

    /**
     * 根据用户id 获取用户信息和用户的角色信息
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 根据用户 id 获取用户信息和用户的角色信息
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById2(Long id);

    /**
     * 测试association的select属性
     * @param id
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 获取所有的用户以及对应的所有角色
     *
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 获取所有的用户以及对应的所有角色, 角色下对应的所有权限
     *
     * @return
     */
    List<SysUser> selectAllUserAndRoles2();

    /**
     * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
     *
     * @param id
     * @return
     */
    SysUser selectAllUserAndRolesSelect(Long id);
}
