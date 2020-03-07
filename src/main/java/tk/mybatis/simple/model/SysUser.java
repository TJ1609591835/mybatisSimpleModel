package tk.mybatis.simple.model;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ClassName: SysUser
 * Package: tk.mybatis.simple.model
 * created By taoJun
 * Description: 用户表
 *
 * @date: 2020/3/3 23:46
 * @author: taoJun
 * @email: 1609591835@qq.com
 */
@Data
public class SysUser {
    /**
     * 用户 ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;
    /**
     * 邮箱
     */
    private String userEmail;
    /**
     * 简介
     */
    private String userInfo;
    /**
     * 头像
     * byte[] 一般对应数据库中的BLOB, LONGVARBINARY 以及一些跟二进制流有关的字段类型
     */
    private byte[] headImg;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 一对一关系
     * 用户角色
     */
    private SysRole role;

    /**
     * 用户的角色集合
     */
    private List<SysRole> roleList;
}
