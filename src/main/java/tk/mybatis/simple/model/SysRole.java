package tk.mybatis.simple.model;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName: SysRole
 * Package: tk.mybatis.simple.model
 * created By taoJun
 * Description: 角色表
 *
 * @date: 2020/3/3 23:46
 * @author: taoJun
 * @email: 1609591835@qq.com
 */
@Data
public class SysRole {
    /**
     * 角色id
     */
    private Long id;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 有效标志
     */
    private Long enabled;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建信息
     */
    private CreateInfo createInfo;

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 角色包含的权限列表
     */
    List<SysPrivilege> privilegeList;
}
