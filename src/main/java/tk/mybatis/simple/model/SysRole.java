package tk.mybatis.simple.model;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 */
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

    public List<SysPrivilege> getPrivilegeList() { return privilegeList; }

    public void setPrivilegeList(List<SysPrivilege> privilegeList) { this.privilegeList = privilegeList; }

    public SysUser getUser() { return user; }

    public void setUser(SysUser user) { this.user = user; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRoleName() { return roleName; }

    public void setRoleName(String roleName) { this.roleName = roleName; }

    public Long getEnabled() { return enabled; }

    public void setEnabled(Long enabled) { this.enabled = enabled; }

    public Long getCreateBy() { return createBy; }

    public void setCreateBy(Long createBy) { this.createBy = createBy; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public CreateInfo getCreateInfo() { return createInfo; }

    public void setCreateInfo(CreateInfo createInfo) { this.createInfo = createInfo; }
}
