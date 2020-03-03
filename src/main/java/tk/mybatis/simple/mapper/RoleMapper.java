package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据角色id 获取角色信息
     *
     * @param roleId
     * @return
     */
     SysRole selectRolesByRoleId(Long roleId);

    /**
     * 根据角色姓名 获取角色信息
     */
    SysRole selectRolesByRoleName(String roleName);

    /**
     * 根据用户 id 和角色的 enabled 状态获取用户的角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

//    /**
//     * 根据用户 id 和 角色的enabled 状态获取用户的角色
//     *
//     * 注: 没有实现方法和测试方法
//     * @param user
//     * @param role
//     * @return
//     */
//    List<SysRole> selectRolesByUserAndRole(@Param("user")SysUser user, @Param("role")SysRole role);

    /**
     * 根据id 获取 role 对象
     * 注: 采用注解的方式
     *
     * @param id
     * @return
     */
    @Select({"select id, role_name, enabled, " +
            "create_by, " +
            "create_time " +
            "from sys_role " +
            "where id = #{id}"})
    SysRole selectById(Long id);

    /**
     * 根据id 获取 Role 对象
     * 注: 在注解的基础上, 使用resultMap 方式
     *
     * @param id
     * @return
     */
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("select id, role_name, enabled, create_by, create_time" +
            "from sys_role where id = #{id}")
    SysRole selectById2(Long id);

    /**
     * 查询全部对象
     */
    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    /**
     * 新增方法
     * 注: 不需要返回主键
     *
     */
    @Insert({"insert into sys_role(id, role_name, enabled, create_by, create_time) " +
            "values(#{id}, #{roleName}, #{enabled}, #{createBy}, " +
            "#{createTime, jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);

    /**
     * 新增方法
     * 注: 返回自增主键
     */
    @Insert({"insert into sys_role(role_name, enabled, create_by, create_time) " +
            "values(#{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert2(SysRole sysRole);

    /**
     * 新增方法
     * 返回非自增主键
     */
    @Insert({"insert into sys_role(role_name, enabled create_by, create_time) " +
            "values(#{roleName}, #{enabled}, #{createBy}, " +
            "#{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",
               keyProperty = "id",
               resultType = Long.class,
               before = false)
    int insert3(SysRole sysRole);

    /**
     * 修改方法
     */
    @Update({"update sys_role " +
            "set role_name = #{roleName}, " +
            "enabled = #{enabled}, " +
            "create_by = #{createBy}, " +
            "create_time = #{createTime, jdbcType=TIMESTAMP} " +
            "where id = #{id}"})
    int updateById(SysRole sysRole);

    /**
     * 删除方法
     */
    @Delete("delete from sys_role where id = #{id}")
    int deleteById(Long id);

    /**
     * 根据传入的roleId查询当前role对象
     */
    SysRole selectRoleById(@Param("id")Long id, @Param("roleName")String roleName);

    /**
     * 为了加深理解 collection 里面的一对多
     * 通过角色查询对应的权限
     * @return
     */
    List<SysRole> selectAllRoleAndPrivileges();
}
