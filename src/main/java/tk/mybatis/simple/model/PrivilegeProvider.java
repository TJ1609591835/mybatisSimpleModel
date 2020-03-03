package tk.mybatis.simple.model;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
    public String selectById(final Long id){
        return new SQL(){
            {
                SELECT("id, privilege_name, privilege_url");
                FROM("sys_privilege");
                WHERE("id = #{id}");
            }
        }.toString();
//     //   第二种写法
//        return "select id, privilege_name, privilege_url "+"from sys_privilege where id = #{id}";
    }
}
