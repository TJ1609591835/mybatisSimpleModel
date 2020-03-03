package tk.mybatis.simple.model;

import lombok.Data;

/**
 * ClassName: Country
 * Package: tk.mybatis.simple.model
 * created By taojun
 * Description: 城市表
 *
 * @date: 2020/3/3 23:46
 * @author: taojun
 * @email: 1609591835@qq.com
 */
@Data
public class Country {
    /**
     * 城市表id
     */
    private Long id;
    /**
     * 城市姓名中文
     */
    private String countryName;
    /**
     * 城市姓名英文
     */
    private String countryCode;
}
