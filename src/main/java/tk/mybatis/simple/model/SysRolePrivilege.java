package tk.mybatis.simple.model;

/**
 * 角色权限关联表
 */
public class SysRolePrivilege {
    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 权限Id
     */
    private Long privilegeId;

    public Long getRoleId() { return roleId; }

    public void setRoleId(Long roleId) { this.roleId = roleId; }

    public Long getPrivilegeId() { return privilegeId; }

    public void setPrivilegeId(Long privilegeId) { this.privilegeId = privilegeId; }
}
