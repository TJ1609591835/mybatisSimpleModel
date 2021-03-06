package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.PrivilegeProvider;
import tk.mybatis.simple.model.SysPrivilege;

import java.util.List;

public interface PrivilegeMapper {
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);

    /**
     * 根据roleId来查询其对应权限
     * @param id
     * @return
     */
    SysPrivilege selectPrivilegeByRoleId(Long id);
}
