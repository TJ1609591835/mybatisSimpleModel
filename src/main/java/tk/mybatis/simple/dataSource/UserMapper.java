package tk.mybatis.simple.dataSource;

import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserMapper {
    List<SysUser> selectAll();
}
